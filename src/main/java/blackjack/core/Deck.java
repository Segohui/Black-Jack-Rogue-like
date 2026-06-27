package blackjack.core;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final PlayingCardFactory cardFactory = new PlayingCardFactory();
    private final List<PlayingCard> cards = new ArrayList<>();

    public void resetToDefaultCards() {
        cards.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(cardFactory.createCard(rank, suit));
            }
        }
    }

    public List<PlayingCard> copyCards() {
        List<PlayingCard> copy = new ArrayList<>();
        for (PlayingCard card : cards) {
            copy.add(new PlayingCard(card));
        }
        return copy;
    }
}
