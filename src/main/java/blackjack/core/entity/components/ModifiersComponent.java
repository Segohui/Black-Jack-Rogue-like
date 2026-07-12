package blackjack.core.entity.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.core.cards.Card;
import blackjack.core.entity.modifiers.DamageModifier;

public class ModifiersComponent {
    private final List<DamageModifier> outputModifiers = new ArrayList<>();
    private final Map<Card, List<DamageModifier>> cardModifiers = new HashMap<>();

    public void addOutputModifier(DamageModifier modifier) {
        outputModifiers.add(modifier);
    }

    public void addCardModifier(Card card, DamageModifier modifier) {
        cardModifiers.putIfAbsent(card, new ArrayList<>());
        cardModifiers.get(card).add(modifier);
    }

    public int applyModifiers(List<Card> cards) {
        int damage = 0;

        for (Card card : cards) {
            int score = card.getBaseScore();
            if (!cardModifiers.containsKey(card)) {
                damage += score;
                continue;
            }
            for (DamageModifier modifier: cardModifiers.get(card)) {
                score = modifier.apply(score);
            }
            damage += score;
        }

        for (DamageModifier modifier: outputModifiers) {
            damage = modifier.apply(damage);
        }
        
        return damage;
    }

    public void clearModifiers() {
        outputModifiers.clear();
    }
}
