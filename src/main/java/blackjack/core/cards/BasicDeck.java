package blackjack.core.cards;

import java.util.ArrayList;
import java.util.List;

import blackjack.core.cards.enums.Rank;
import blackjack.core.cards.enums.Suit;

public class BasicDeck {
    private final List<Card> cards;

    public BasicDeck() {
        PlayingCardFactory cardFactory = new PlayingCardFactory();
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(cardFactory.createCard(rank, suit));
            }
        }

        this.cards = cards;
    }

    public List<Card> copyBasicDeck() {
        List<Card> copy = new ArrayList<>();
        for (Card card : cards) {
            copy.add(new Card(card));
        }
        return copy;
    }
}
