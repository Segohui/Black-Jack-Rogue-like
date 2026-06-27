package blackjack.core;

import java.util.Collections;
import java.util.List;

public class Stack {
    private final Deck deck;
    private List<PlayingCard> cards;

    public Stack(Deck deck) {
        this.deck = deck;
        cards = deck.copyCards();
        Collections.shuffle(cards);
    }

    public void resetStack() {
        cards = deck.copyCards();
        Collections.shuffle(cards);
    }

    public PlayingCard takeCard() {
        if (cards.isEmpty()) {
            resetStack();
        }
        return cards.removeLast();
    }
}
