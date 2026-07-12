package blackjack.core.battle;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.DataSignal;
import blackjack.core.EmptySignal;
import blackjack.core.battle.states.State;
import blackjack.core.battle.states.StateFactory;
import blackjack.core.cards.Card;
import blackjack.dto.CardDrawEventDTO;
import blackjack.dto.CombatOverDTO;
import blackjack.dto.DamageEventDTO;
import blackjack.entity.Entity;
import blackjack.entity.enemy.AIRecord;
import blackjack.entity.enemy.behaviors.Behavior;

public class BattleCore {
    private final EmptySignal playerTurn = new EmptySignal();

    private final DataSignal<String> roundOverData = new DataSignal<>();
    private final DataSignal<CombatOverDTO> combatOverData = new DataSignal<>();

    private final Entity player;
    private int globalStand = 21; // may change with power ups

    private Entity enemy;
    private Behavior enemyBehavior;
    private StateFactory stateFactory;
    private State state;

    public BattleCore(Entity player) {
        this.player = player;
    }

    public void startCombat() {
        resetCore();
        activateStartRoundState();
    }

    private void resetCore() {
        this.state = null;
        this.stateFactory = new StateFactory(player, enemy, enemyBehavior);
    }

    public void resetEnemy(AIRecord enemyRecord) {
        this.enemy = enemyRecord.entity();
        this.enemyBehavior = enemyRecord.behavior();
    }

    public void playerHit() {
        state.hit(this);
    }

    public void playerStand() {
        state.stand(this);
    }

    public void playerUsePurchasedCard(int idx){
        state.useBoughtCard(this, idx);
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

    public String getPlayerName() { return player.getName(); }
    public String getEnemyName() { return enemy.getName(); }
    public int getPlayerCurrentHp() { return player.getCurrentHp(); }
    public int getEnemyCurrentHp() { return enemy.getCurrentHp(); }
    public List<Card> getPlayerCards() { return player.getCards(); }
    public List<Card> getEnemyCards() { return enemy.getCards(); }
    public int getGlobalStand() { return globalStand; }
    public Entity getPlayer() { return player; }
}
