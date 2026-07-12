package blackjack.core.battle.states;

import blackjack.entity.Entity;
import blackjack.entity.enemy.behaviors.Behavior;

public class StateFactory {
    private final Entity player;
    private final Entity enemy;
    private final Behavior enemyBehavior;

    public StateFactory(Entity player, Entity enemy, Behavior enemyBehavior) {
        this.player = player;
        this.enemy = enemy;
        this.enemyBehavior = enemyBehavior;
    }

    public State createStartRoundState() {
        return new StartRoundState(player, enemy);
    }

    public State createPlayerTurnState() {
        return new PlayerTurnState(player);
    }

    public State createPlayerOnlyState() {
        return new PlayerOnlyState(player);
    }

    public State createEnemyTurnState() {
        return new EnemyTurnState(enemy, enemyBehavior);
    }

    public State createEnemyOnlyState() {
        return new EnemyOnlyTurnState(enemy, enemyBehavior);
    }

    public State createEndTurnState() {
        return new EndTurnState(player, enemy);
    }

    public State createEndGameState() {
        return new EndGameState(player, enemy);
    }
}
