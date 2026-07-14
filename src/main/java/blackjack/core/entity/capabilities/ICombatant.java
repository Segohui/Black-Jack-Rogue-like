package blackjack.core.entity.capabilities;

import blackjack.core.cards.Card;
import blackjack.core.entity.modifiers.DamageModifier;

/**
 * Defines combat-related behavior such as attack calculation and modifiers.
 */
public interface ICombatant {
    int calculateAttackDamage();
    void addDamageOutputModifier(DamageModifier modifier);
    void addDamageCardModifier(Card card, DamageModifier modifier);
    void battleReset();
}