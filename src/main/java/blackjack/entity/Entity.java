package blackjack.entity;

import java.util.List;

import blackjack.core.cards.Card;

public interface Entity {
    String getName();
    List<Card> getCards();
    int calculateHandSum();
    List<Card> drawInitialCards(int amount);
    void roundReset();
    void battleReset();
    Card hit();
    int getCurrentHp();
    void takeDamage(int damage);
    void heal(int amount);
    boolean isAlive();
}
