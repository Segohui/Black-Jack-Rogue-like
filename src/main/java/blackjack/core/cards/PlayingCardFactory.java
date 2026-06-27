package blackjack.core.cards;

import blackjack.core.cards.enums.Rank;
import blackjack.core.cards.enums.Suit;

public class PlayingCardFactory {
    public Card createCard(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }
}
