package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.entity.Entity;
import blackjack.entity.enemy.behaviors.Behavior;

public class EnemyTurnState implements State {
    private final Entity enemy;
    private final Behavior enemyBehavior;

    public EnemyTurnState(Entity enemy, Behavior enemyBehavior) {
        this.enemy = enemy;
        this.enemyBehavior = enemyBehavior;
    }

    @Override
    public void handle(BattleCore core) {
        int globalStand = core.getGlobalStand();
        enemyBehavior.playTurn(enemy, globalStand);

        if (enemyBehavior.hasStopped()) {
            if (enemyBehavior.hasLost()) {
                core.activateEndTurnState();
            } else {
                core.activatePlayerOnlyTurnState();
            }
        } else {
            core.activatePlayerTurnState();
        }
    }
}
