package blackjack.core.entity.modifiers;

/**
 * Transforms a damage value after a card attack is calculated.
 */
public interface DamageModifier {
    int apply(int damage);
}
