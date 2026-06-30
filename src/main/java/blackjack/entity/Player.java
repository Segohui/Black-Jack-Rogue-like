package blackjack.entity;

import blackjack.entity.components.DeckComponent;
import blackjack.entity.components.HealthComponent;

public class Player implements CombatEntity {
    private final DeckComponent deckComponent;
    private final HealthComponent healthComponent;
    private final String name;

    public Player(String name) {
        this.name = name;
        this.deckComponent = new DeckComponent();
        this.healthComponent = new HealthComponent(30);
    }

    public void resetPlayer() {
        deckComponent.resetHand();
        deckComponent.resetStack();
        healthComponent.resetHp();
    }

    @Override
    public boolean isPlayerControlled() { return true; }

    @Override
    public String getName() { return name; }

    @Override
    public DeckComponent getDeckComponent() { return deckComponent; }

    @Override
    public HealthComponent getHealthComponent() { return healthComponent; }
}
