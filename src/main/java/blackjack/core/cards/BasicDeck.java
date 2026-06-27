package blackjack.core.cards;

import java.util.ArrayList;
import java.util.List;

import blackjack.core.cards.enums.Rank;
import blackjack.core.cards.enums.Suit;

public class BasicDeck {
    private final PlayingCardFactory cardFactory = new PlayingCardFactory();
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
            copy.add(new Card(card));
        }
        return copy;
    }
}
