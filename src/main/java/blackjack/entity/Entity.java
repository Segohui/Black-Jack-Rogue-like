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
    void addPurchasedCard(Card card);
    Card usePurchasedCard(int idx);
    boolean hasPurchasedCards();
    List<Card> getPurchasedCards();
    int getGold();
    boolean canAfford(int cost);
    void spend(int cost);
    void addGold(int amount);
}
