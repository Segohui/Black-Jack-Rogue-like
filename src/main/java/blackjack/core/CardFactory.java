package blackjack.core;

public interface CardFactory {
    public Card createCard(Rank rank, Suit suit);
}
