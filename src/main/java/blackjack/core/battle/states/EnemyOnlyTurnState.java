package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.entity.Entity;
import blackjack.entity.enemy.behaviors.Behavior;

public class EnemyOnlyTurnState implements State {
    private final Entity enemy;
    private final Behavior enemyBehavior;

    public EnemyOnlyTurnState(Entity enemy, Behavior enemyBehavior) {
        this.enemy = enemy;
        this.enemyBehavior = enemyBehavior;
    }

    @Override
    public void handle(BattleCore core) {
        int globalStand = core.getGlobalStand();
        enemyBehavior.playTurn(enemy, globalStand);

        while (!enemyBehavior.hasStopped(enemy, globalStand)) {
            enemy.hit();
        }

        core.activateEndTurnState();
    }
}
