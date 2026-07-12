package blackjack.core.cards;

import java.util.Objects;

import blackjack.core.cards.enums.Rank;
import blackjack.core.cards.enums.Suit;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card(Card card) {
        this.rank = card.getRank();
        this.suit = card.getSuit();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

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
