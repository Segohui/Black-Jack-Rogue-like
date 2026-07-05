package blackjack.entity.components;

import java.util.ArrayList;
import java.util.List;

import blackjack.core.cards.Card;
import blackjack.core.cards.Deck;
import blackjack.core.cards.Hand;
import blackjack.core.cards.HandEvaluator;
import blackjack.core.cards.Stack;

public class CardsComponent {
    private final Deck deck = new Deck();
    private final Stack stack = new Stack(deck);
    private final Hand hand = new Hand();
    private final HandEvaluator handEvaluator = new HandEvaluator();

    public void resetHand() {
        hand.reset();
    }

    public void resetStack() {
        stack.reset();
    }

    public List<Card> drawCardToHand(int amount) {
        List<Card> drawnCards = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Card card = stack.takeCard();
            hand.addCard(card);
            drawnCards.add(card);
        }
        
        return drawnCards;
    }

    public int calculateHandSum() {
        return handEvaluator.calculateSum(hand.getCards());
    }

    public List<Card> getCards() { return hand.getCards(); }
}
