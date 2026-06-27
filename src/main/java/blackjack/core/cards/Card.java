package blackjack.core.cards;

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
}
