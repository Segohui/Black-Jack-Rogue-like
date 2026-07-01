package blackjack.core.states.helper;

import blackjack.core.BlackjackCore;
import blackjack.core.cards.Card;
import blackjack.entity.components.DeckComponent;

public class DrawCardHelper {
    public static void playerDrawCard(DeckComponent playerDeckComponent, BlackjackCore core, int amount) {
        Card card = playerDeckComponent.drawCardToHand(amount).get(0);
        core.registerPlayerCardDraw(card);
        core.emitDrawCard();
    }

    public static void enemyDrawCard(DeckComponent enemyDeckComponent, BlackjackCore core, int amount) {
        Card card = enemyDeckComponent.drawCardToHand(amount).get(0);
        core.registerEnemyCardDraw(card);
        core.emitDrawCard();
    }
}
