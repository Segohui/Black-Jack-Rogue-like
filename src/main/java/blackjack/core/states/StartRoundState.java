package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.components.DeckComponent;

public class StartRoundState implements State {
    private final DeckComponent playerDeckComponent;
    private final DeckComponent enemyDeckComponent;

    public StartRoundState(DeckComponent playerDeckComponent, DeckComponent enemyDeckComponent) {
        this.playerDeckComponent = playerDeckComponent;
        this.enemyDeckComponent = enemyDeckComponent;
    }

    public void handle(BlackjackCore core) {
        playerDeckComponent.resetHand();
        enemyDeckComponent.resetHand();

        playerDeckComponent.drawCardToHand(2);
        enemyDeckComponent.drawCardToHand(2);

        core.activatePlayerTurnState();
    }
}
