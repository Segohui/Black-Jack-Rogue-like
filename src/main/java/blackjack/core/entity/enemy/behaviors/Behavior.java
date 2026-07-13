package blackjack.core.entity.enemy.behaviors;

import blackjack.core.entity.Entity;

public interface Behavior {
    void playTurn(Entity entity, int globalStand);
    boolean hasStopped();
    boolean hasLost();
}