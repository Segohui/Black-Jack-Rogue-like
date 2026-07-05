package blackjack.entity;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.entity.components.CardsComponent;
import blackjack.entity.components.HealthComponent;

public class CombatEntity implements Entity {
    private final CardsComponent cardsComponent;
    private final HealthComponent healthComponent;
    private final String name;

    public CombatEntity(String name, int maxHp) {
        this.name = name;
        this.cardsComponent = new CardsComponent();
        this.healthComponent = new HealthComponent(maxHp);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return cardsComponent.getCards();
    }

    @Override
    public int calculateHandSum() {
        return cardsComponent.calculateHandSum();
    }
    
    @Override
    public List<Card> drawInitialCards(int amount) {
        return cardsComponent.drawCardToHand(amount);
    }

    @Override
    public void roundReset() {
        cardsComponent.resetHand();
    }

    @Override
    public void battleReset() {
        healthComponent.resetHp();
        cardsComponent.resetStack();
        roundReset();
    }

    @Override
    public Card hit() {
        Card card = cardsComponent.drawCardToHand(1).get(0);
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
