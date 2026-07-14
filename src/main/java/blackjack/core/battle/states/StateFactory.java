package blackjack.core.battle.states;

import blackjack.core.entity.capabilities.Entity;
import blackjack.core.entity.enemy.behaviors.Behavior;
import blackjack.core.inventory.Inventory;

public class StateFactory {
    private final Entity player;
    private final Entity enemy;
    private final Behavior enemyBehavior;
    private final Inventory playerInventory;
    private final Inventory enemyInventory;

    public StateFactory(Entity player, Entity enemy, Behavior enemyBehavior,
            Inventory playerInventory, Inventory enemyInventory) {
        this.player = player;
        this.enemy = enemy;
        this.enemyBehavior = enemyBehavior;
        this.playerInventory = playerInventory;
        this.enemyInventory = enemyInventory;
    }

    public State createStartRoundState() {
        return new StartRoundState(player, enemy);
    }

    public State createPlayerTurnState() {
        return new PlayerTurnState(player, playerInventory);
    }

    public State createPlayerOnlyState() {
        return new PlayerOnlyState(player, playerInventory);
    }

    public State createEnemyTurnState() {
        return new EnemyTurnState(enemy, enemyBehavior);
    }

    public State createEnemyOnlyState() {
        return new EnemyOnlyTurnState(enemy, enemyBehavior);
    }

    public State createEndTurnState() {
        return new EndRoundState(player, enemy, playerInventory, enemyInventory);
    }

    public State createEndGameState() {
        return new EndGameState(player, enemy, playerInventory, enemyInventory);
    }
}
