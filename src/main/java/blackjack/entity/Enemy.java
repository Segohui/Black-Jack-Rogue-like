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
    private int currentHp;
    private int maxHp;
    private int standThreshold;
    private final String name;

    public Enemy(int maxHp, int standThreshold, String name) {
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.standThreshold = standThreshold;
        this.name = name;
    }

    @Override
    public Card drawCardToHand() {
        Card card = stack.takeCard();
        hand.addCard(card);
        return card;
    }

    @Override
    public void resetHand() {
        hand.resetHand();
    }

    @Override
    public int calculateSum() {
        return handEvaluator.calculateSum(hand.getCards());
    }

    @Override
    public boolean isAlive() {
        if (this.currentHp > 0) {
            return true;
        }

        return false;
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHp -= damage;
    }

    public int getStandThreshold() { return standThreshold; }
    
    @Override
    public boolean isPlayerControlled() { return false; }

    @Override
    public List<Card> getCards() { return hand.getCards(); }

    @Override
    public int getCurrentHp() { return currentHp; }
    
    @Override
    public int getMaxHp() { return maxHp; }

    @Override
    public String getName() { return name; }
}
