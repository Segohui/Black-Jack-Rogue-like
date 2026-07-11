package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.entity.Behavior;
import blackjack.entity.Entity;

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
        enemyBehavior.playTurn(globalStand);
        int handSum = enemy.calculateHandSum();

        while (!enemyBehavior.hasStopped(globalStand)) {
            enemy.hit();
        }
        if (handSum <= globalStand) {
            enemy.stand();
        }
        core.activateEndTurnState();
    }
}
