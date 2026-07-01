package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.core.states.helper.DrawCardHelper;
import blackjack.entity.components.DeckComponent;

public class PlayerTurnState implements State {
    private final DeckComponent deckComponent;

    public PlayerTurnState(DeckComponent deckComponent) {
        this.deckComponent = deckComponent;
    }

    public void handle(BlackjackCore core) {
        core.emitNextTurn();
    }

    @Override
    public void hit(BlackjackCore core) {
        DrawCardHelper.playerDrawCard(deckComponent, core, 1);

        int handSum = deckComponent.calculateHandSum();

        if (handSum > core.getGlobalStand()) {
            core.activateEndTurnState();
        } else {
            core.activateEnemyTurnState();
        }
    }

    @Override
    public void stand(BlackjackCore core) {
        core.activateEnemyOnlyTurnState();
    }
}
