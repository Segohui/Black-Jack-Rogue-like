package blackjack.core.entity.enemy.behaviors;

import blackjack.core.entity.capabilities.ICardUser;

/**
 * Defines the decision logic an enemy uses during its turn.
 */
public interface Behavior {
    void playTurn(ICardUser entity, int globalStand);
    boolean hasStopped();
    boolean hasLost();
}