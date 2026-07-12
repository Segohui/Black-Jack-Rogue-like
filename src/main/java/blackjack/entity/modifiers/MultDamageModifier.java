package blackjack.entity.modifiers;

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
