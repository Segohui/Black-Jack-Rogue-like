package blackjack.entity;

import java.util.List;

import blackjack.core.cards.Card;

public interface CombatEntity {
    int calculateSum();
    Card drawCardToHand();
    boolean isPlayerControlled();
    int getHp();
    List<Card> getCards();
    String getName();
    void takeDamage();
}
