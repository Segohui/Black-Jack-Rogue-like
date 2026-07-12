package blackjack.entity.enemy;

import blackjack.core.inventory.Inventory;
import blackjack.entity.Entity;
import blackjack.entity.enemy.behaviors.Behavior;

public record AIRecord(Entity entity, Behavior behavior, Inventory inventory) {}
