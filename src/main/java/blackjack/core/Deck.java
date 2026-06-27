package blackjack.core;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final CardFactory cardFactory = new PlayingCardFactory();
    private final List<Card> cards = new ArrayList<>();

    public void resetToDefaultCards() {
        cards.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(cardFactory.createCard(rank, suit));
            }
        }
    }

    public List<Card> copyCards() {
        List<Card> copy = new ArrayList<>();
        for (Card card : cards) {
            copy.add(new PlayingCard(card));
        }
        return copy;
    }
}
