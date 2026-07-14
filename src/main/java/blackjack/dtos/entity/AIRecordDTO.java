package blackjack.dtos.entity;

import blackjack.core.entity.capabilities.Entity;
import blackjack.core.entity.enemy.behaviors.Behavior;
import blackjack.core.inventory.Inventory;

public record AIRecordDTO(Entity entity, Behavior behavior, Inventory inventory) {}
