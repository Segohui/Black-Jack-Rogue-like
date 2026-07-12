package blackjack.entity.enemy.behaviors;

import java.util.concurrent.ThreadLocalRandom;
import blackjack.entity.Entity;
import java.lang.Math;

public class PercentageBehavior implements Behavior {
    private final double standPercentage;
    private boolean stopped = false;
    private boolean lost = false;


    public PercentageBehavior(double standPercentage) {
        this.standPercentage = standPercentage;
    }

    @Override
    public void playTurn(Entity entity, int globalStand) {
        stopped = false;
        lost = false;

        int handSum = entity.calculateHandSum();
        
        if(handSum > globalStand){
            stopped = true;
            lost = true;
            return;
        }

        double standValue = calculateStandChance(handSum, globalStand);
        int roll = ThreadLocalRandom.current().nextInt(100);

        // it was always 0 cause it was int division. Changed to 100.0
        if ((roll / 100.0) < standValue || handSum == globalStand) {
            stopped = true;
            entity.stand();
        } else {
            entity.hit();
                if (entity.calculateHandSum() > globalStand) {
                stopped = true;
                lost = true;
            }
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

    private double calculateStandChance(int handSum, int globalStand) {

        // changed to double too
        return Math.clamp( Math.pow(((double) (handSum - 10)/ (globalStand - 10)), standPercentage), 0.0, 1.0);
    }
}