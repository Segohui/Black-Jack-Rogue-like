package blackjack.core.battle;

import java.util.function.Consumer;
import blackjack.core.battle.states.State;
import blackjack.core.battle.states.StateFactory;
import blackjack.core.cards.Card;
import blackjack.core.cards.Deck;
import blackjack.core.entity.capabilities.Entity;
import blackjack.core.entity.enemy.behaviors.Behavior;
import blackjack.core.inventory.Inventory;
import blackjack.core.signal.DataSignal;
import blackjack.core.signal.EmptySignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.CombatOverDTO;
import blackjack.dtos.core.battle.DamageEventDTO;
import blackjack.dtos.core.battle.EntityStateDTO;
import blackjack.dtos.entity.AIRecordDTO;

public class BattleCore {
    private final EmptySignal playerTurn = new EmptySignal();

    private final DataSignal<String> roundOverData = new DataSignal<>();
    private final DataSignal<CombatOverDTO> combatOverData = new DataSignal<>();

    private final Entity player;
    private final Deck playerDeck;
    private final Inventory playerInventory;
    private int globalStand = 21; // may change with items

    private Entity enemy;
    private Behavior enemyBehavior;
    private Inventory enemyInventory;
    private StateFactory stateFactory;
    private State state;

    public BattleCore(Entity player, Deck playerDeck, Inventory playerInventory) {
        this.player = player;
        this.playerDeck = playerDeck;
        this.playerInventory = playerInventory;
    }

    public void startCombat() {
        resetCore();
        activateStartRoundState();
    }

    private void resetCore() {
        this.state = null;
        this.stateFactory = new StateFactory(player, enemy, enemyBehavior,
                playerInventory, enemyInventory);
    }

    public void prepareForNewBattle(AIRecordDTO enemyRecord) {
        roundOverData.clearConnections();
        combatOverData.clearConnections();
        playerTurn.clearConnections();
        player.clearSignals();
        resetEnemy(enemyRecord);
    }

    private void resetEnemy(AIRecordDTO enemyRecord) {
        this.enemy = enemyRecord.entity();
        this.enemyBehavior = enemyRecord.behavior();
        this.enemyInventory = enemyRecord.inventory();
    }

    private void clearSignals() {
        playerTurn.clearConnections();
        roundOverData.clearConnections();
        combatOverData.clearConnections();
    }

    public void playerHit() {
        state.hit(this);
    }

    public void playerStand() {
        state.stand(this);
    }

    public void playerUseItem(int idx){
        state.useItem(this, idx);
    }

    public int calculatePlayerSum() {
        return player.calculateHandSum();
    }

    public int calculateEnemySum() {
        return enemy.calculateHandSum();
    }

    // maybe find a better way to activate states and the other repetitive stuff
    private void transitionTo(State newState) {
        this.state = newState;
        this.state.handle(this);
    }

    public void activateStartRoundState() { 
        transitionTo(stateFactory.createStartRoundState());
    }

    public void activatePlayerTurnState() { 
        transitionTo(stateFactory.createPlayerTurnState()); 
    }

    public void activatePlayerOnlyTurnState() { 
        transitionTo(stateFactory.createPlayerOnlyState());
    }

    public void activateEnemyTurnState() { 
        transitionTo(stateFactory.createEnemyTurnState()); 
    }

    public void activateEnemyOnlyTurnState() { 
        transitionTo(stateFactory.createEnemyOnlyState()); 
    }

    public void activateEndTurnState() { 
        transitionTo(stateFactory.createEndTurnState());
    }

    public void activateEndGameState() { 
        transitionTo(stateFactory.createEndGameState());
    }

    // Signal Handling

    public void playerTurnConnect(Runnable runnable) { playerTurn.connect(runnable); }
    public void emitPlayerTurn() { playerTurn.emit(); }

    public void roundOverDataConnect(Consumer<String> listener) { roundOverData.connect(listener); }
    public void combatOverDataConnect(Consumer<CombatOverDTO> listener) { combatOverData.connect(listener); }

    public void emitRoundOverData(String name) { roundOverData.emit(name); }
    public void emitCombatOverData(String name, boolean isPlayerControlled, int goldReward) { combatOverData.emit(new CombatOverDTO(name, isPlayerControlled, goldReward)); }
    
    public void drawCardPlayerConnect(Consumer<CardDrawEventDTO> listerner) { player.drawCardConnect(listerner); }
    public void playerStandConnect(Consumer<String> listener) { player.entityStandConnect(listener); }
    public void playerTakeDamageConnect(Consumer<DamageEventDTO> listener) { player.takeDamageConnect(listener); }

    public void enemyTakeDamageConnect(Consumer<DamageEventDTO> listener) { enemy.takeDamageConnect(listener); }
    public void drawCardEnemyConnect(Consumer<CardDrawEventDTO> listener) { enemy.drawCardConnect(listener); }
    public void enemyStandConnect(Consumer<String> listener) { enemy.entityStandConnect(listener); }

    // Getters

    public int getGlobalStand() { return globalStand; }

    public BattleContextDTO getBattleContextDTO() {
        return new BattleContextDTO(player, enemy, playerDeck);
    }

    public EntityStateDTO getPlayerData() {
        return new EntityStateDTO(
            player.getName(),
            player.calculateHandSum(),
            player.getCards().stream().map(Card::toString).toList(),
            player.getCurrentHp()
        );
    }

    public EntityStateDTO getEnemyData() {
        return new EntityStateDTO(
            enemy.getName(),
            enemy.calculateHandSum(),
            enemy.getCards().stream().map(Card::toString).toList(),
            enemy.getCurrentHp()
        );
    }

    public EntityStateDTO getEntityStateDataByName(String entityName) {
        if (entityName.isEmpty() || entityName.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (entityName.equals(player.getName())) {
            return getPlayerData();
        } else {
            return getEnemyData();
        }
    }
}
