package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.components.DeckComponent;
import blackjack.entity.components.CurrencyComponent;
import blackjack.entity.components.HealthComponent;

public class EndGameState implements State {
    private static final int GOLD_REWARD = 25;
    private final CurrencyComponent playerCurrencyComponent;
    private final HealthComponent playerHealthComponent;
    private final DeckComponent playerDeckComponent;


    public EndGameState(HealthComponent playerHealthComponent, DeckComponent playerDeckComponent, CurrencyComponent playerCurrencyComponent) {
        this.playerHealthComponent = playerHealthComponent;
        this.playerDeckComponent = playerDeckComponent;
        this.playerCurrencyComponent = playerCurrencyComponent;
    }

    public void handle(BlackjackCore core) {
        if (!playerHealthComponent.isAlive()) {
            core.registerEnemyGameWin();
        } else {
            playerHealthComponent.resetHp();
            playerDeckComponent.resetHand();
            playerDeckComponent.resetStack();
            playerCurrencyComponent.add(GOLD_REWARD);

            core.registerPlayerGameWin();
        }

        core.emitCombatOver();
    }
}
