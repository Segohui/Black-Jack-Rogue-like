package blackjack.core.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;

import blackjack.core.cards.enums.Rank;
import blackjack.core.cards.enums.Suit;
import blackjack.core.signal.DataSignal;

/**
 * Represents a full deck of playing cards used by the game.
 *
 * <p>This class provides a mutable deck model and emits events when cards are
 * burnt from the deck.</p>
 */
public class Deck {
    private final DataSignal<Card> cardBurnt = new DataSignal<>();

    private final MultiSet<Card> cards;

    /**
     * Creates a standard 52-card deck.
     */
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

    /**
     * Returns a copy of the cards currently in the deck.
     *
     * @return list of copied cards
     */
    public List<Card> copyDeckCards() {
        List<Card> copy = new ArrayList<>();
        for (Card card : cards) {
            copy.add(new Card(card));
        }
        return copy;
    }

    /**
     * Adds a card to the deck.
     *
     * @param card card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Removes a card from the deck and emits a burn event.
     *
     * @param card card to burn from the deck
     */
    public void burnCard(Card card) {
        cards.remove(card);

    }

    /**
     * Connects a listener to card burnt events.
     *
     * @param consumer callback invoked when a card is removed from the deck
     */
    public void cardBurntConnect(Consumer<Card> consumer) {
        cardBurnt.connect(consumer);
    }
}
