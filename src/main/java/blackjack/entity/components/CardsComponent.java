package blackjack.entity.components;

import java.util.ArrayList;
import java.util.Collections;
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
    private final List<Card> purchasedCards = new ArrayList<>();

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

    public void addPurchasedCard(Card card){
        purchasedCards.add(card);
    }

    public Card usePurchasedCard(int idx){
        if(idx < 0 || idx >= purchasedCards.size()){
            throw new IllegalArgumentException("Invalid purchased card index");
        }
        
        Card card = purchasedCards.remove(idx);
        hand.addCard(card);
        return card;
    }

    public boolean hasPurchasedCards(){
        return !purchasedCards.isEmpty();
    }

    public int calculateHandSum() {
        return handEvaluator.calculateSum(hand.getCards());
    }

    public List<Card> getCards() { return hand.getCards(); }
    public List<Card> getPurchasedCards() { return Collections.unmodifiableList(purchasedCards);}
}
