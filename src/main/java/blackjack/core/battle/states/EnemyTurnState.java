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
        int handSum = enemy.calculateHandSum();

        if (handSum > globalStand) {
            core.activateEndTurnState();
        } else {
            if (enemyBehavior.hasStopped(enemy, globalStand)) {
                core.activatePlayerOnlyTurnState();
            } else {
                core.activatePlayerTurnState();
            }
        }
    }
}
