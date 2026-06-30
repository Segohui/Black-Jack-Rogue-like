package blackjack.entity;

import blackjack.entity.components.DeckComponent;
import blackjack.entity.components.HealthComponent;

public interface CombatEntity {
    boolean isPlayerControlled();
    String getName();
    DeckComponent getDeckComponent();
    HealthComponent getHealthComponent();
}
