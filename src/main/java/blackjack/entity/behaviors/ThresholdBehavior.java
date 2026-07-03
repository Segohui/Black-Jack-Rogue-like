package blackjack.entity.behaviors;

import blackjack.entity.Behavior;
import blackjack.entity.Entity;

public class ThresholdBehavior implements Behavior {
    private final Entity entity;
    private final int standThreshold;

    public ThresholdBehavior(Entity entity, int standThreshold) {
        this.entity = entity;
        this.standThreshold = standThreshold;
    }

    @Override
    public void playTurn(int globalStand) {
        int standValue = calculateStandValue(globalStand);
        int handSum = entity.calculateHandSum();
        if (handSum < standValue) {
            entity.hit();
        }
    }

    @Override
    public boolean hasStopped(int globalStand) {
        return (calculateStandValue(globalStand) >= standThreshold);
    }

    private int calculateStandValue(int globalStand) {
        return Math.max(globalStand - standThreshold, 0);
    }
}
