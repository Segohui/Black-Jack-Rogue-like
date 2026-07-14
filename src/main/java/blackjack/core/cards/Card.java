package blackjack.core.cards;

import java.util.Objects;

import blackjack.core.cards.enums.Rank;
import blackjack.core.cards.enums.Suit;

/**
 * Represents a playing card with rank and suit.
 *
 * <p>This value object is immutable and provides card-specific helpers for
 * scoring and display.</p>
 */
public class Card {
    private final Rank rank;
    private final Suit suit;

    /**
     * Creates a new card with the specified rank and suit.
     *
     * @param rank card rank
     * @param suit card suit
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Creates a copy of an existing card.
     *
     * @param card card to copy
     */
    public Card(Card card) {
        this.rank = card.getRank();
        this.suit = card.getSuit();
    }

    /**
     * Returns the card rank.
     *
     * @return card rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns the card suit.
     *
     * @return card suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the base score for this card rank.
     *
     * @return base score value
     */
    public int getBaseScore() {
        return BaseRankScores.getScore(rank);
    }

    /**
     * Checks whether the card is a face card.
     *
     * @return true when the card is jack, queen, or king
     */
    public boolean isFaceCard() {
        return this.rank == Rank.RANK_J
                || this.rank == Rank.RANK_K
                || this.rank == Rank.RANK_Q;
    }

    /**
     * Checks whether the card is a number card.
     *
     * @return true when the card is a numeric value other than ace
     */
    public boolean isNumberCard() {
        return this.rank != Rank.RANK_A && !isFaceCard();
    }

    /**
     * Returns a string representation of the card.
     *
     * @return string form of the card
     */
    @Override
    public String toString() {
        return this.rank.toString() + " | " + this.suit.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Card other = (Card) o;
        
        return (other.rank == this.rank
                && other.suit == this.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
