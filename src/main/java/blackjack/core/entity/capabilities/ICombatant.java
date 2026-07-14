package blackjack.core.entity.capabilities;

import blackjack.core.cards.Card;
import blackjack.core.entity.modifiers.DamageModifier;

public interface ICombatant {
    int calculateAttackDamage();
    void addDamageOutputModifier(DamageModifier modifier);
    void addDamageCardModifier(Card card, DamageModifier modifier);
    void battleReset();
}