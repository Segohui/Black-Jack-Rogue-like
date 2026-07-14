package blackjack.dtos.entity;

import blackjack.core.entity.capabilities.Entity;
import blackjack.core.entity.enemy.behaviors.Behavior;
import blackjack.core.inventory.Inventory;

/**
 * Stores the runtime record used to drive enemy behavior in battle.
 *
 * @param entity combat entity controlled by the AI
 * @param behavior turn logic for the AI
 * @param inventory inventory carried by the AI entity
 */
public record AIRecordDTO(Entity entity, Behavior behavior, Inventory inventory) {}
