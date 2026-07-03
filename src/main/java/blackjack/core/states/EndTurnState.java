package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.Entity;

public class EndTurnState implements State {
    private final Entity player;
    private final Entity enemy;

    public EndTurnState(Entity player, Entity enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void handle(BlackjackCore core) {
        int playerSum = player.calculateHandSum();
        int enemySum = enemy.calculateHandSum();
        int globalStand = core.getGlobalStand();

        if (playerSum == enemySum) {
            endTurn(core);
            
            return;
        }

        if (playerWin(globalStand, playerSum, enemySum)) {
            enemy.takeDamage(playerSum);
            core.registerPlayerTurnWin(playerSum);
            core.emitTakeDamage();
            if (!enemy.isAlive()) {
                core.activateEndGameState();
                return;
            }
        } else {
            player.takeDamage(enemySum);
            core.registerEnemyTurnWin(enemySum);
            core.emitTakeDamage();
            if (!player.isAlive()) {
                core.activateEndGameState();
                return;
            }
        }

        endTurn(core);
    }

    private void endTurn(BlackjackCore core) {
        core.emitRoundOver();
        core.resetWinner();
        core.activateStartRoundState();
    }

    private boolean playerWin(int globalStand, int playerSum, int enemySum) {
        return ((playerSum <= globalStand)
                && (playerSum > enemySum || enemySum > globalStand));
    }
}
