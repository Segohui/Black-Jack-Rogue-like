package blackjack.entity;

import java.util.List;

import blackjack.core.cards.Card;

public interface CombatEntity {
    int getHp();
    List<Card> getCards();
    int calculateSum();
    Card drawCardToHand();
    boolean isPlayerControlled();
}
