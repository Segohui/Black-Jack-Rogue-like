package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.Entity;

public class PlayerOnlyState implements State {
    private final Entity player;

    public PlayerOnlyState(Entity player) {
        this.player = player;
    }

    @Override
    public void handle(BlackjackCore core) {
        int handSum = player.calculateHandSum();

        if (handSum > core.getGlobalStand()) {
            core.activateEndTurnState();
        }

        core.emitPlayerTurn();
    }

    @Override
    public void hit(BlackjackCore core) {
        player.hit();

        int handSum = player.calculateHandSum();

        if (handSum > core.getGlobalStand()) {
            core.activateEndTurnState();
        } else {
            core.activatePlayerOnlyTurnState();
        }
    }

    @Override
    public void stand(BlackjackCore core) {
        core.activateEndTurnState();
    }
}
