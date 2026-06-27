package blackjack.core;

import java.util.List;

public class Player {
    private final Deck deck = new Deck();
    private final Stack stack = new Stack(deck);
    private final Hand hand = new Hand();
    private final HandEvaluator handEvaluator = new HandEvaluator();

    public Player() {
        deck.resetToDefaultCards();
    }

    public Card drawCardToHand() {
        Card card = stack.takeCard();
        hand.addCard(card);
        return card;
    }

    public int calculateSum() {
        return handEvaluator.calculateSum(hand.getCards());
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
