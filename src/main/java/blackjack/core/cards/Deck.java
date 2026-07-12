package blackjack.core.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;

import blackjack.core.DataSignal;
import blackjack.core.cards.enums.Rank;
import blackjack.core.cards.enums.Suit;

public class Deck {
    private final DataSignal<Card> cardBurnt = new DataSignal<>();

    private final MultiSet<Card> cards;

    public Deck() {
        MultiSet<Card> cards = new HashMultiSet<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }

        this.cards = cards;
    }

    public List<Card> copyDeckCards() {
        List<Card> copy = new ArrayList<>();
        for (Card card : cards) {
            copy.add(new Card(card));
        }
        return copy;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void burnCard(Card card) {
        cards.remove(card);

    }

    public void cardBurntConnect(Consumer<Card> consumer) {
        cardBurnt.connect(consumer);
    }
}
