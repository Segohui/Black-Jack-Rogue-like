package blackjack.core.cards;

import java.util.Collections;
import java.util.List;

/**
 * Represents the draw stack for a combat entity.
 */
public class Stack {
    private List<Card> cards;
    private final Deck deck;

    public Stack(Deck deck) {
        this.cards = deck.copyDeckCards();
        this.deck = deck;
        Collections.shuffle(cards);
    }

    public void reset() {
        cards = deck.copyDeckCards();
        Collections.shuffle(cards);
    }

    public Card takeCard() {
        if (cards.isEmpty()) {
            reset();
        }

        Card drawnCard = cards.removeLast();
        return drawnCard;
    }

    public Card peekCard() {
        if (cards.isEmpty()) {
            reset();
        }

        return cards.getLast();
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("Card does not exist");
        }
        cards.add(card);
    }
}
