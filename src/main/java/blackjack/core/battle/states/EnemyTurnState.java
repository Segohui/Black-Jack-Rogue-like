package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.entity.Behavior;
import blackjack.entity.Entity;

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
        enemyBehavior.playTurn(globalStand);
        int handSum = enemy.calculateHandSum();

        if (handSum > globalStand) {
            core.activateEndTurnState();
        } else {
            if (enemyBehavior.hasStopped(globalStand)) {
                enemy.stand();
                core.activatePlayerOnlyTurnState();
            } else {
                core.activatePlayerTurnState();
            }
        }
    }
}
