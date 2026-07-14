package blackjack.core.entity.modifiers;

public class DoubleSumModifier implements SumModifier {
 
    @Override
    public int apply(int sum) {
      
        return sum * 2;
    
    }
}