package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.entity.capabilities.ICardUser;
import blackjack.core.entity.enemy.behaviors.Behavior;

/**
 * Handles the enemy turn when the player is not taking an action in the same phase.
 */
public class EnemyOnlyTurnState implements State {
    private final ICardUser enemy;
    private final Behavior enemyBehavior;

    public EnemyOnlyTurnState(ICardUser enemy, Behavior enemyBehavior) {
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
