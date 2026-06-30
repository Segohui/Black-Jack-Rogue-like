package blackjack.entity;

import blackjack.entity.components.BehaviorComponent;
import blackjack.entity.components.DeckComponent;
import blackjack.entity.components.HealthComponent;

public class Enemy implements CombatEntity {
    private final DeckComponent deckComponent;
    private final HealthComponent healthComponent;
    private final BehaviorComponent behaviorComponent;
    private final String name;

    public Enemy(int maxHp, int standThreshold, String name) {
        this.deckComponent = new DeckComponent();
        this.healthComponent = new HealthComponent(maxHp);
        this.behaviorComponent = new BehaviorComponent(standThreshold);
        this.name = name;
    }
    
    @Override
    public boolean isPlayerControlled() { return false; }

    @Override
    public String getName() { return name; }

    @Override
    public DeckComponent getDeckComponent() { return deckComponent; }

    @Override
    public HealthComponent getHealthComponent() { return healthComponent; }

    public BehaviorComponent getBehaviorComponent() { return behaviorComponent; }
}
