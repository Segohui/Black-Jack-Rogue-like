package blackjack.entity;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.entity.components.DeckComponent;
import blackjack.entity.components.HealthComponent;

public class CombatEntity implements Entity {
    private final DeckComponent deckComponent;
    private final HealthComponent healthComponent;
    private final String name;

    public CombatEntity(String name, int maxHp) {
        this.name = name;
        this.deckComponent = new DeckComponent();
        this.healthComponent = new HealthComponent(maxHp);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return deckComponent.getCards();
    }

    @Override
    public int calculateHandSum() {
        return deckComponent.calculateHandSum();
    }
    
    @Override
    public List<Card> drawInitialCards(int amount) {
        return deckComponent.drawCardToHand(amount);
    }

    @Override
    public void roundReset() {
        deckComponent.resetHand();
    }

    @Override
    public void battleReset() {
        healthComponent.resetHp();
        deckComponent.resetStack();
        roundReset();
    }

    @Override
    public Card hit() {
        Card card = deckComponent.drawCardToHand(1).get(0);
        return card;
    }

    @Override
    public int getCurrentHp() {
        return healthComponent.getCurrentHp();
    }

    @Override
    public void takeDamage(int damage) {
        healthComponent.takeDamage(damage);
    }

    @Override
    public void heal(int amount) {
        healthComponent.heal(amount);
    }

    @Override
    public boolean isAlive() {
        return (healthComponent.getCurrentHp() > 0);
    }
}
