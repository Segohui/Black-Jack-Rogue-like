package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.entity.capabilities.ICardUser;
import blackjack.core.entity.enemy.behaviors.Behavior;

public class EnemyTurnState implements State {
    private final ICardUser enemy;
    private final Behavior enemyBehavior;

    public EnemyTurnState(ICardUser enemy, Behavior enemyBehavior) {
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
