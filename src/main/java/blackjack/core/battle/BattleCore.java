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

/**
 * Core battle engine that manages combat state, signals, and participant data.
 *
 * <p>This class implements the battle workflow using the state pattern. It
 * owns the current player and enemy entities, routes turn actions to the
 * active state, and exposes domain events through signal connectors.</p>
 */
public class BattleCore {
    private final EmptySignal playerTurn = new EmptySignal();

    private final DataSignal<String> roundOverData = new DataSignal<>();
    private final DataSignal<CombatOverDTO> combatOverData = new DataSignal<>();
    private final DataSignal<Boolean> gameOver = new DataSignal<>();


    private final Entity player;
    private final Deck playerDeck;
    private final Inventory playerInventory;
    private int globalStand = 21; // may change with items

    private Entity enemy;
    private Behavior enemyBehavior;
    private Inventory enemyInventory;
    private StateFactory stateFactory;
    private State state;

    /**
     * Creates a new battle core for the player with a shared deck and inventory.
     *
     * @param player the player entity that participates in combat
     * @param playerDeck deck used for player card draws
     * @param playerInventory player's inventory used for item effects
     */
    public BattleCore(Entity player, Deck playerDeck, Inventory playerInventory) {
        this.player = player;
        this.playerDeck = playerDeck;
        this.playerInventory = playerInventory;
    }

    /**
     * Starts a new combat encounter by resetting state and entering the first round.
     */
    public void startCombat() {
        resetCore();
        activateStartRoundState();
    }

    /**
     * Reinitializes the state factory and clears the active state.
     */
    private void resetCore() {
        this.state = null;
        this.stateFactory = new StateFactory(player, enemy, enemyBehavior,
                playerInventory, enemyInventory);
    }

    /**
     * Prepares the battle core for a new enemy using the supplied record.
     *
     * @param enemyRecord record containing enemy entity, behavior, and inventory
     */
    public void prepareForNewBattle(AIRecordDTO enemyRecord) {
        roundOverData.clearConnections();
        combatOverData.clearConnections();
        playerTurn.clearConnections();
        player.clearSignals();
        resetEnemy(enemyRecord);
    }

    /**
     * Resets the current enemy state using a new enemy descriptor.
     *
     * @param enemyRecord record containing the next enemy details
     */
    private void resetEnemy(AIRecordDTO enemyRecord) {
        if (enemy != null) {
            enemy.clearSignals();
        }

        this.enemy = enemyRecord.entity();
        this.enemyBehavior = enemyRecord.behavior();
        this.enemyInventory = enemyRecord.inventory();
    }

    /**
     * Delegates a player hit action to the current battle state.
     */
    public void playerHit() {
        state.hit(this);
    }

    /**
     * Delegates a player stand action to the current battle state.
     */
    public void playerStand() {
        state.stand(this);
    }

    /**
     * Delegates a player item use action to the current battle state.
     *
     * @param idx index of the item to use in the player's inventory
     */
    public void playerUseItem(int idx){
        state.useItem(this, idx);
    }

    /**
     * Returns the current sum of the player's hand.
     *
     * @return player's hand value
     */
    public int calculatePlayerSum() {
        return player.calculateHandSum();
    }

    /**
     * Returns the current sum of the enemy's hand.
     *
     * @return enemy's hand value
     */
    public int calculateEnemySum() {
        return enemy.calculateHandSum();
    }

    // maybe find a better way to activate states and the other repetitive stuff
    /**
     * Transitions the battle engine to a new state and executes its handler.
     *
     * @param newState new state that will control battle behavior
     */
    private void transitionTo(State newState) {
        this.state = newState;
        this.state.handle(this);
    }

    /**
     * Activates the start round state.
     */
    public void activateStartRoundState() { 
        transitionTo(stateFactory.createStartRoundState());
    }

    /**
     * Activates the player turn state.
     */
    public void activatePlayerTurnState() { 
        transitionTo(stateFactory.createPlayerTurnState()); 
    }

    /**
     * Activates the player-only turn state.
     */
    public void activatePlayerOnlyTurnState() { 
        transitionTo(stateFactory.createPlayerOnlyState());
    }

    /**
     * Activates the enemy turn state.
     */
    public void activateEnemyTurnState() { 
        transitionTo(stateFactory.createEnemyTurnState()); 
    }

    /**
     * Activates the enemy-only turn state.
     */
    public void activateEnemyOnlyTurnState() { 
        transitionTo(stateFactory.createEnemyOnlyState()); 
    }

    /**
     * Activates the end turn state.
     */
    public void activateEndTurnState() { 
        transitionTo(stateFactory.createEndTurnState());
    }

    /**
     * Activates the end game state.
     */
    public void activateEndGameState() { 
        transitionTo(stateFactory.createEndGameState());
    }

    // Signal Handling

    /**
     * Connects a listener for the player's turn event.
     *
     * @param runnable callback invoked when the player turn starts
     */
    public void playerTurnConnect(Runnable runnable) { playerTurn.connect(runnable); }

    /**
     * Connects a listener for round-over events.
     *
     * @param listener callback invoked with the name of the entity who ended the round
     */
    public void roundOverConnect(Consumer<String> listener) { roundOverData.connect(listener); }

    /**
     * Connects a listener for combat-over events.
     *
     * @param listener callback invoked when combat ends
     */
    public void combatOverConnect(Consumer<CombatOverDTO> listener) { combatOverData.connect(listener); }

    /**
     * Connects a listener for game-over events.
     *
     * @param listener callback invoked with whether the player is alive
     */
    public void gameOverConnect(Consumer<Boolean> listener) { gameOver.connect(listener); }

    /**
     * Emits the player turn signal.
     */
    public void emitPlayerTurn() { playerTurn.emit(); }

    /**
     * Emits a round-over signal with the winning entity name.
     *
     * @param name name of the entity that ended the round
     */
    public void emitRoundOver(String name) { roundOverData.emit(name); }

    /**
     * Emits a game-over signal for the current combat.
     *
     * @param playerAlive true when the player survived
     */
    public void emitGameOver(boolean playerAlive) { gameOver.emit(playerAlive); }

    /**
     * Emits a combat-over event containing summary data.
     *
     * @param name name of the entity that ended combat
     * @param isPlayerControlled whether the entity was player controlled
     * @param goldReward gold awarded at the end of combat
     */
    public void emitCombatOver(String name, boolean isPlayerControlled, int goldReward) {
                combatOverData.emit(new CombatOverDTO(name, isPlayerControlled, goldReward)); }
    
    /**
     * Subscribes to the player's draw-card event.
     *
     * @param listerner callback invoked when the player draws a card
     */
    public void drawCardPlayerConnect(Consumer<CardDrawEventDTO> listerner) { player.drawCardConnect(listerner); }

    /**
     * Subscribes to the player's stand event.
     *
     * @param listener callback invoked when the player stands
     */
    public void playerStandConnect(Consumer<String> listener) { player.entityStandConnect(listener); }

    /**
     * Subscribes to the player's damage event.
     *
     * @param listener callback invoked when the player takes damage
     */
    public void playerTakeDamageConnect(Consumer<DamageEventDTO> listener) { player.takeDamageConnect(listener); }

    /**
     * Subscribes to the enemy's damage event.
     *
     * @param listener callback invoked when the enemy takes damage
     */
    public void enemyTakeDamageConnect(Consumer<DamageEventDTO> listener) { enemy.takeDamageConnect(listener); }

    /**
     * Subscribes to the enemy's draw-card event.
     *
     * @param listener callback invoked when the enemy draws a card
     */
    public void drawCardEnemyConnect(Consumer<CardDrawEventDTO> listener) { enemy.drawCardConnect(listener); }

    /**
     * Subscribes to the enemy's stand event.
     *
     * @param listener callback invoked when the enemy stands
     */
    public void enemyStandConnect(Consumer<String> listener) { enemy.entityStandConnect(listener); }

    // Getters

    /**
     * Returns the current global stand threshold for blackjack scoring.
     *
     * @return the current stand limit value
     */
    public int getGlobalStand() { return globalStand; }

    /**
     * Builds a DTO containing the current battle context.
     *
     * @return a snapshot of the current battle context
     */
    public BattleContextDTO getBattleContextDTO() {
        return new BattleContextDTO(player, enemy, playerDeck);
    }

    /**
     * Builds a DTO representing the player's current state.
     *
     * @return player's state data for the UI layer
     */
    public EntityStateDTO getPlayerData() {
        return new EntityStateDTO(
            player.getName(),
            player.calculateHandSum(),
            player.getCards().stream().map(Card::toString).toList(),
            player.getCurrentHp()
        );
    }

    /**
     * Builds a DTO representing the enemy's current state.
     *
     * @return enemy's state data for the UI layer
     */
    public EntityStateDTO getEnemyData() {
        return new EntityStateDTO(
            enemy.getName(),
            enemy.calculateHandSum(),
            enemy.getCards().stream().map(Card::toString).toList(),
            enemy.getCurrentHp()
        );
    }

    /**
     * Returns the state data for the requested entity.
     *
     * @param entityName name of the player or enemy entity
     * @return the matching entity state DTO
     * @throws IllegalArgumentException when entityName is blank
     */
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
