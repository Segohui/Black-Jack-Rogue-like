package blackjack.core.battle.states;

import blackjack.core.entity.capabilities.Entity;
import blackjack.core.entity.enemy.behaviors.Behavior;
import blackjack.core.inventory.Inventory;

/**
 * Factory for creating concrete battle states used by {@link BattleCore}.
 *
 * <p>This class centralizes state creation and encapsulates the game rules for
 * state transitions.</p>
 */
public class StateFactory {
    private final Entity player;
    private final Entity enemy;
    private final Behavior enemyBehavior;
    private final Inventory playerInventory;
    private final Inventory enemyInventory;

    /**
     * Creates a state factory for the current combat participants.
     *
     * @param player player entity in battle
     * @param enemy enemy entity in battle
     * @param enemyBehavior behavior strategy for enemy turns
     * @param playerInventory inventory available during player turns
     * @param enemyInventory inventory available for the enemy
     */
    public StateFactory(Entity player, Entity enemy, Behavior enemyBehavior,
            Inventory playerInventory, Inventory enemyInventory) {
        this.player = player;
        this.enemy = enemy;
        this.enemyBehavior = enemyBehavior;
        this.playerInventory = playerInventory;
        this.enemyInventory = enemyInventory;
    }

    /**
     * Creates the initial start round state.
     *
     * @return new start round state
     */
    public State createStartRoundState() {
        return new StartRoundState(player, enemy);
    }

    /**
     * Creates the player turn state.
     *
     * @return new player turn state
     */
    public State createPlayerTurnState() {
        return new PlayerTurnState(player, playerInventory);
    }

    /**
     * Creates the player-only turn state used when the enemy has no action.
     *
     * @return new player-only state
     */
    public State createPlayerOnlyState() {
        return new PlayerOnlyState(player, playerInventory);
    }

    /**
     * Creates the enemy turn state.
     *
     * @return new enemy turn state
     */
    public State createEnemyTurnState() {
        return new EnemyTurnState(enemy, enemyBehavior);
    }

    /**
     * Creates the enemy-only turn state used when the player has no action.
     *
     * @return new enemy-only state
     */
    public State createEnemyOnlyState() {
        return new EnemyOnlyTurnState(enemy, enemyBehavior);
    }

    /**
     * Creates the end-of-turn state responsible for resolving effects.
     *
     * @return new end round state
     */
    public State createEndTurnState() {
        return new EndRoundState(player, enemy, playerInventory, enemyInventory);
    }

    /**
     * Creates the end-of-combat state responsible for battle termination.
     *
     * @return new end game state
     */
    public State createEndGameState() {
        return new EndGameState(player, enemy, playerInventory, enemyInventory);
    }
}
