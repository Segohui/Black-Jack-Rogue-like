package blackjack.core.cards;

import java.util.Collections;
import java.util.List;

public class Stack {
    private List<Card> cards;
    private final Deck deck;

    public Stack(Deck deck) {
        this.cards = deck.copyBasicDeck();
        this.deck = deck;
        Collections.shuffle(cards);
    }

    public void resetStack() {
        cards = deck.copyBasicDeck();
        Collections.shuffle(cards);
    }

    public Card takeCard() {
        if (cards.isEmpty()) {
            resetStack();
        }

        Card drawnCard = cards.removeLast();
        return drawnCard;
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("Card does not exist");
        }
        cards.add(card);
    }
}
