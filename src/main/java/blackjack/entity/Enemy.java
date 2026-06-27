package blackjack.entity;

import java.util.List;

import blackjack.core.cards.BasicDeck;
import blackjack.core.cards.Card;
import blackjack.core.cards.Hand;
import blackjack.core.cards.HandEvaluator;
import blackjack.core.cards.Stack;

public class Enemy implements CombatEntity {
    private final BasicDeck deck = new BasicDeck();
    private final Stack stack = new Stack(deck);
    private final Hand hand = new Hand();
    private final HandEvaluator handEvaluator = new HandEvaluator();
    private int hp;
    private int standValue;

    public Enemy(int hp, int standValue) {
        this.hp = hp;
        this.standValue = standValue;
    }

    public Card drawCardToHand() {
        Card card = stack.takeCard();
        hand.addCard(card);
        return card;
    }

    public int calculateSum() {
        return handEvaluator.calculateSum(hand.getCards());
    }
    
    public boolean isPlayerControlled() { return false; }
    public List<Card> getCards() { return hand.getCards(); }
    public int getHp() { return hp; }
    public int getStandValue() { return standValue; }
}
