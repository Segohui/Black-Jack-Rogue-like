package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.Entity;

public class PlayerTurnState implements State {
    private final Entity player;

    public PlayerTurnState(Entity player) {
        this.player = player;
    }

    @Override
    public void handle(BlackjackCore core) {
        core.emitPlayerTurn();
    }

    @Override
    public void hit(BlackjackCore core) {
        player.hit();

        int handSum = player.calculateHandSum();

        if (handSum > core.getGlobalStand()) {
            core.activateEndTurnState();
        } else {
            core.activateEnemyTurnState();
        }
    }

    @Override
    public void stand(BlackjackCore core) {
        core.activateEnemyOnlyTurnState();
    }
}
