package blackjack.core.entity.enemy.behaviors;

import blackjack.core.entity.capabilities.ICardUser;

public interface Behavior {
    void playTurn(ICardUser entity, int globalStand);
    boolean hasStopped();
    boolean hasLost();
}