package blackjack.core.entity.modifiers;

public class HalveSumModifier implements SumModifier {

    @Override
    public int apply(int sum) {
        
        return sum / 2;
    }
}