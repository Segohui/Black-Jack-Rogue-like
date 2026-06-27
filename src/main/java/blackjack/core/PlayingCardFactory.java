package blackjack.core;

public class PlayingCardFactory {
    public PlayingCard createCard(Rank rank, Suit suit) {
        return new PlayingCard(rank, suit);
    }
}
