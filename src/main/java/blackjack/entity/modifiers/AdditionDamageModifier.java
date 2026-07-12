package blackjack.entity.modifiers;

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
