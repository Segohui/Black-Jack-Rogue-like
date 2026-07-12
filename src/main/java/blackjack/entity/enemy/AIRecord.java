package blackjack.entity.enemy;

import blackjack.entity.Entity;
import blackjack.entity.enemy.behaviors.Behavior;

public record AIRecord(Entity entity, Behavior behavior) {}
