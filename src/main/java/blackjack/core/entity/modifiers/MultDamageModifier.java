package blackjack.core.entity.modifiers;

/**
 * Multiplies a damage value by a fixed factor.
 */
public class MultDamageModifier implements DamageModifier {
    private final int mult;

    public MultDamageModifier(int mult) {
        this.mult = mult;
    }

    @Override
    public int apply(int damage) {
        return damage * mult;
    }
}
