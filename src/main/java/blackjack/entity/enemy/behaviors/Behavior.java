package blackjack.entity.enemy.behaviors;

import blackjack.entity.Entity;

public interface Behavior {
    void playTurn(Entity entity, int globalStand);
    boolean hasStopped();
    boolean hasLost();
}