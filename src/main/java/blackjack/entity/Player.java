package blackjack.entity;

import java.util.List;

import blackjack.core.cards.BasicDeck;
import blackjack.core.cards.Card;
import blackjack.core.cards.Hand;
import blackjack.core.cards.HandEvaluator;
import blackjack.core.cards.Stack;

public class Player implements CombatEntity {
    // get a copy of the deck when combat starts
    // if the deck is empty, get another copy
    private final BasicDeck deck = new BasicDeck();
    private final Stack stack = new Stack(deck);
    private final Hand hand = new Hand();
    private final HandEvaluator handEvaluator = new HandEvaluator();
    private final String name;
    private int hp;

    public Player(String name) {
        this.name = name;
        this.hp = 5;
    }

    public Card drawCardToHand() {
        Card card = stack.takeCard();
        hand.addCard(card);
        return card;
    }

    public int calculateSum() {
        return handEvaluator.calculateSum(hand.getCards());
    }

    public void resetPlayerCards() {
        stack.resetStack();
        hand.resetHand();
    }

    public void takeDamage() {
        
    }

    public boolean isPlayerControlled() { return true; }
    public List<Card> getCards() { return hand.getCards(); }
    public int getHp() { return hp; }
    public String getName() { return name; }
}
