package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.components.DeckComponent;
import blackjack.entity.components.HealthComponent;

public class EndGameState implements State {
    private final HealthComponent playerHealthComponent;
    private final DeckComponent playerDeckComponent;

    public EndGameState(HealthComponent playerHealthComponent, DeckComponent playerDeckComponent) {
        this.playerHealthComponent = playerHealthComponent;
        this.playerDeckComponent = playerDeckComponent;
    }

    public void handle(BlackjackCore core) {
        if (!playerHealthComponent.isAlive()) {
            core.registerEnemyGameWin();
        } else {
            playerHealthComponent.resetHp();
            playerDeckComponent.resetHand();
            playerDeckComponent.resetStack();

            core.registerPlayerGameWin();
        }

        core.emitCombatOver();
    }
}
