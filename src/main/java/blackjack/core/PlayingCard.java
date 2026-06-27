package blackjack.core;

public class PlayingCard {
    private Rank rank;
    private Suit suit;

    public PlayingCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public PlayingCard(PlayingCard card) {
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
