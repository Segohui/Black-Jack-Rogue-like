package blackjack.core.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stack {
    private List<Card> cards;
    private List<Card> discardPile;

    public Stack(BasicDeck deck) {
        this.cards = deck.copyBasicDeck();
        this.discardPile = new ArrayList<>();
        Collections.shuffle(cards);
    }

    public void resetStack() {
        cards.addAll(discardPile);
        discardPile.clear();
        Collections.shuffle(cards);
    }

    public Card takeCard() {
        if (cards.isEmpty()) {
            resetStack();
        }

        Card drawnCard = cards.removeLast();
        discardPile.add(drawnCard);
        return drawnCard;
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("Card does not exist");
        }
        cards.add(card);
    }
}
