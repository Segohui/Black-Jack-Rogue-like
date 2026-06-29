package blackjack.entity;

import java.util.List;

import blackjack.core.cards.Deck;
import blackjack.core.cards.Card;
import blackjack.core.cards.Hand;
import blackjack.core.cards.HandEvaluator;
import blackjack.core.cards.Stack;

public class Enemy implements CombatEntity {
    private final Deck deck = new Deck();
    private final Stack stack = new Stack(deck);
    private final Hand hand = new Hand();
    private final HandEvaluator handEvaluator = new HandEvaluator();
    private int hp;
    private int standThreshold;
    private final String name;

    public Enemy(int hp, int standThreshold, String name) {
        this.hp = hp;
        this.standThreshold = standThreshold;
        this.name = name;
    }

    public Card drawCardToHand() {
        Card card = stack.takeCard();
        hand.addCard(card);
        return card;
    }

    public int calculateSum() {
        return handEvaluator.calculateSum(hand.getCards());
    }

    public void takeDamage() {
        
    }
    
    public boolean isPlayerControlled() { return false; }
    public List<Card> getCards() { return hand.getCards(); }
    public int getHp() { return hp; }
    public int getStandThreshold() { return standThreshold; }
    public String getName() { return name; }
}
