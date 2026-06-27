package blackjack.core;

public class PlayingCardFactory implements CardFactory {
    @Override
    public PlayingCard createCard(Rank rank, Suit suit) {
        return new PlayingCard(rank, suit);
    }
}
