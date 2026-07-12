package blackjack.entity.enemy.behaviors;

import blackjack.entity.Entity;

public class ThresholdBehaviour implements Behavior {
    private final int standThreshold;

    public ThresholdBehaviour(int standThreshold) {
        this.standThreshold = standThreshold;
    }

    @Override
    public void playTurn(Entity entity, int globalStand) {
        int standValue = calculateStandValue(globalStand);
        int handSum = entity.calculateHandSum();
        
        if (handSum < standValue) {
            entity.hit();
        } else {
            entity.stand();
        }
    }

    @Override
    public boolean hasStopped(Entity entity, int globalStand) {
        int handSum = entity.calculateHandSum();
        return (handSum >= calculateStandValue(globalStand));
    }

    private int calculateStandValue(int globalStand) {
        return Math.max(globalStand - standThreshold, 0);
    }
}