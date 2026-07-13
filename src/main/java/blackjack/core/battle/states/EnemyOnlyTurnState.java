package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.entity.Entity;
import blackjack.core.entity.enemy.behaviors.Behavior;

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

        while (!enemyBehavior.hasStopped() && !enemyBehavior.hasLost()) {
            enemyBehavior.playTurn(enemy, globalStand);
        }

        core.activateEndTurnState();
    }
}
