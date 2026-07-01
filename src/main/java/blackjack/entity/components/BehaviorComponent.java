package blackjack.entity.components;

public class BehaviorComponent {
    private final int standThreshold;

    public BehaviorComponent(int standThreshold) {
        this.standThreshold = standThreshold;
    }

    public int calculateStandValue(int globalStand) {
        return Math.max(globalStand - standThreshold, 0);
    }

    public int getStandThreshold() { return standThreshold; }
}
