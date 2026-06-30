package blackjack.entity;

import java.util.List;

import blackjack.core.cards.Card;

public interface CombatEntity {
    int calculateSum();
    Card drawCardToHand();
    boolean isPlayerControlled();
    int getCurrentHp();
    int getMaxHp();
    List<Card> getCards();
    String getName();
    void takeDamage(int damage);
    boolean isAlive();
    void resetHand();
}
