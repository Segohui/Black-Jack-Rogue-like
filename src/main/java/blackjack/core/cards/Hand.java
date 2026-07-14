package blackjack.core.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a hand of cards held by a combatant.
 *
 * <p>This class encapsulates the hand operations and preserves immutability
 * when exposing the card list.</p>
 */
public class Hand {
    private final List<Card> cards = new ArrayList<>();

    /**
     * Adds a card to the hand.
     *
     * @param card card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Clears all cards from the hand.
     */
    public void reset() {
        cards.clear();
    }

    /**
     * Discards the last card drawn into the hand.
     *
     * @return discarded card
     */
    public Card discardLast() {
        return cards.removeLast();
    }

    /**
     * Returns an unmodifiable view of the cards in hand.
     *
     * @return list of cards in hand
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * Replaces the hand contents with a new set of cards.
     *
     * @param newCards new hand contents
     */
    public void setCards(List<Card> newCards) {
        cards.clear();
        cards.addAll(newCards);
    }
}
