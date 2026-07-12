package blackjack.core.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void reset() {
        cards.clear();
    }

    public Card discardLast() {
        return cards.removeLast();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
