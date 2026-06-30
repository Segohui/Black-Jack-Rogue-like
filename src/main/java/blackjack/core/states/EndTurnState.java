package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.components.DeckComponent;
import blackjack.entity.components.HealthComponent;

public class EndTurnState implements State {
    private final HealthComponent playerHealthComponent;
    private final HealthComponent enemyHealthComponent;
    private final DeckComponent playerDeckComponent;
    private final DeckComponent enemyDeckComponent;

    public EndTurnState(HealthComponent playerHealthComponent, HealthComponent enemyHealthComponent, DeckComponent playerDeckComponent, DeckComponent enemyDeckComponent) {
        this.playerHealthComponent = playerHealthComponent;
        this.enemyHealthComponent = enemyHealthComponent;
        this.playerDeckComponent = playerDeckComponent;
        this.enemyDeckComponent = enemyDeckComponent;
    }

    public void handle(BlackjackCore core) {
        int playerSum = playerDeckComponent.calculateHandSum();
        int enemySum = enemyDeckComponent.calculateHandSum();
        int globalStand = core.getGlobalStand();

        if (playerSum == enemySum) {
            core.activatePlayerTurnState();
            return;
        }

        if (playerWin(globalStand, playerSum, enemySum)) {
            enemyHealthComponent.takeDamage(playerSum);
            core.registerPlayerTurnWin(playerSum);
            core.emitTakeDamage();
            if (!enemyHealthComponent.isAlive()) {
                core.activateEndGameState();
                return;
            }
        } else {
            playerHealthComponent.takeDamage(enemySum);
            core.registerEnemyTurnWin(enemySum);
            core.emitTakeDamage();
            if (!playerHealthComponent.isAlive()) {
                core.activateEndGameState();
                return;
            }
        }

        core.emitRoundOver();

        resetHands();
        core.activateStartRoundState();
    }

    private boolean playerWin(int globalStand, int playerSum, int enemySum) {
        if (playerSum <= globalStand) {
            if (playerSum > enemySum || enemySum > globalStand) {
                return true;
            }

            return false;
        }

        return false;
    }

    private void resetHands() {
        enemyDeckComponent.resetHand();
        playerDeckComponent.resetHand();
    }
}
