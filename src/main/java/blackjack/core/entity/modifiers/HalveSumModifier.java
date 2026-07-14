package blackjack.core.entity.modifiers;

/**
 * Halves the current hand sum for the remainder of the round.
 */
public class HalveSumModifier implements SumModifier {

    @Override
    public int apply(int sum) {
        
        return sum / 2;
    }
}