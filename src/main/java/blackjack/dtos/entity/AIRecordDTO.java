package blackjack.dtos.entity;

import blackjack.core.inventory.Inventory;
import blackjack.entity.Entity;
import blackjack.entity.enemy.behaviors.Behavior;

public record AIRecordDTO(Entity entity, Behavior behavior, Inventory inventory) {}
