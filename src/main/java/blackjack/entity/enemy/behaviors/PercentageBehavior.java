package blackjack.entity.enemy.behaviors;

import java.util.concurrent.ThreadLocalRandom;
import blackjack.entity.Entity;
import java.lang.Math;

public class PercentageBehavior implements Behavior {
    private final double standPercentage;
    private boolean stopped = false;


    public PercentageBehavior(double standPercentage) {
        this.standPercentage = standPercentage;
    }

    @Override
    public void playTurn(Entity entity, int globalStand) {
        if(stopped) return;

        int handSum = entity.calculateHandSum();
        if(handSum > globalStand){
            stopped = true;
            return;
        }

        double standValue = calculateStandChance(handSum, globalStand);
        
        int roll = ThreadLocalRandom.current().nextInt(100);

        if ((roll / 100) < standValue || handSum == globalStand) {
            stopped = true;
            entity.stand();

        } else {
            entity.hit();
        }
    }

    @Override
    public boolean hasStopped(Entity entity, int globalStand) {
        return stopped;
    }

    private double calculateStandChance(int handSum, int globalStand) {

        return Math.clamp( Math.pow(((handSum - 10)/ (globalStand - 10)), standPercentage), 0.0, 1.0);
    }
}