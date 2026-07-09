package blackjack.entity.components;

import blackjack.entity.Behavior;

public class BehaviorComponent {
    private final Behavior behavior;

    public BehaviorComponent(Behavior behavior) {
        this.behavior = behavior;
    }

    public void playTurn(int globalStand) {
        behavior.playTurn(globalStand);
    }

    public boolean hasStopped(int globalStand) {
        return behavior.hasStopped(globalStand);
    }
}
