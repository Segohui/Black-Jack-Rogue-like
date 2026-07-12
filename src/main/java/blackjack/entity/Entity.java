package blackjack.entity;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.dto.CardDrawEventDTO;
import blackjack.dto.DamageEventDTO;
import blackjack.entity.modifiers.DamageModifier;

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
    boolean isPlayerControlled();
    Card discardLastCardInHand();
    int calculateAttackDamage();
    void addDamageOutputModifier(DamageModifier modifier);
    void addDamageCardModifier(Card card, DamageModifier modifier);

    // Signals handling

    void clearSignals();

    void drawCardConnect(Consumer<CardDrawEventDTO> listener);
    void emitDrawCard(CardDrawEventDTO eventData);

    void entityStandConnect(Consumer<String> listener);
    void emitEntityStand();

    void takeDamageConnect(Consumer<DamageEventDTO> listener);
    void emitTakeDamage(DamageEventDTO eventData);
}
