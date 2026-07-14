package blackjack.core.entity.modifiers;

/**
 * Transforms a hand sum value used by the entity state.
 */
public interface SumModifier {
    
    int apply(int sum);

}