package blackjack.core.entity.modifiers;

/**
 * Doubles the current hand sum for the remainder of the round.
 */
public class DoubleSumModifier implements SumModifier {
 
    @Override
    public int apply(int sum) {
      
        return sum * 2;
    
    }
}