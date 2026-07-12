package blackjack.entity.enemy.behaviors;

import blackjack.entity.Entity;

public class ThresholdBehaviour implements Behavior {
    private final int standThreshold;
    private boolean stopped;
    private boolean lost = false;

    public ThresholdBehaviour(int standThreshold) {
        this.standThreshold = standThreshold;
    }

    @Override
    public void playTurn(Entity entity, int globalStand) {
        stopped = false;
        lost = false;

        int standValue = calculateStandValue(globalStand);
        int handSum = entity.calculateHandSum();

        if(handSum > globalStand){
            stopped = true;
            lost = true;
            return;
        }
        
        if (handSum < standValue) {
            entity.hit();
            if (entity.calculateHandSum() > globalStand) {
                stopped = true;
                lost = true;
            }
        } else {
            stopped = true;
            entity.stand();
        }
    }

    @Override
    public boolean hasStopped() {
        return stopped;
    }

    @Override
    public boolean hasLost() {
        return lost;
    }

    private int calculateStandValue(int globalStand) {
        return Math.max(globalStand - standThreshold, 0);
    }
}