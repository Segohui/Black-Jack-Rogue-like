package blackjack.core.entity.modifiers;

/**
 * Adds a fixed amount of damage to the computed attack value.
 */
public class AdditionDamageModifier implements DamageModifier {
    private final int extra;

    public AdditionDamageModifier(int extra) {
        this.extra = extra;
    }

    @Override
    public int apply(int damage) {
        return damage + extra;
    }
}
