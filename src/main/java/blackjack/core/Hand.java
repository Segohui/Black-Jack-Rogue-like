package blackjack.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<PlayingCard> cards = new ArrayList<>();

    public void addCard(PlayingCard card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public List<PlayingCard> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
