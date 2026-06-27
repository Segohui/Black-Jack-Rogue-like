package blackjack.core;

public class PlayingCard implements Card {
    private Rank rank;
    private Suit suit;

    public PlayingCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public PlayingCard(Card card) {
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
