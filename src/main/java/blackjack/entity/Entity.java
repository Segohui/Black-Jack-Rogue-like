package blackjack.entity;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.dto.CardDrawEventDTO;
import blackjack.dto.DamageEventDTO;

public interface Entity {
    String getName();
    List<Card> getCards();
    int calculateHandSum();
    List<Card> drawInitialCards(int amount);
    void roundReset();
    void battleReset();
    void hit();
    void stand();
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
    boolean isPlayerControlled();
    void clearSignals();

    // Signals handling

    void drawCardConnect(Consumer<CardDrawEventDTO> listener);
    void emitDrawCard(CardDrawEventDTO eventData);

    void entityStandConnect(Consumer<String> listener);
    void emitEntityStand();

    void takeDamageConnect(Consumer<DamageEventDTO> listener);
    void emitTakeDamage(DamageEventDTO eventData);
}
