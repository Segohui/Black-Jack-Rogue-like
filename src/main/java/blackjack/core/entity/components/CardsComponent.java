package blackjack.core.entity.components;

import java.util.ArrayList;
import java.util.List;

import blackjack.core.cards.Card;
import blackjack.core.cards.Deck;
import blackjack.core.cards.Hand;
import blackjack.core.cards.HandEvaluator;
import blackjack.core.cards.Stack;

public class CardsComponent {
    private final Stack stack;
    private final Hand hand = new Hand();
    private final HandEvaluator handEvaluator = new HandEvaluator();

    public CardsComponent(Deck deck) {
        this.stack = new Stack(deck);
    }

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

    public Card discardLastCardInHand() {
        return hand.discardLast();
    }

    public int calculateHandSum() {
        return handEvaluator.calculateSum(hand.getCards());
    }

    public Card peekNextCard() {
        return stack.peekCard();
    }

    public List<Card> setHand(List<Card> newCards) {
        List<Card> oldCards = new ArrayList<>(hand.getCards());
        hand.setCards(newCards);
        return oldCards;
    }

    public List<Card> getCards() { return hand.getCards(); }
}
