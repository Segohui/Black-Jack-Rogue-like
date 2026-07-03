package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.Entity;

public class EndGameState implements State {
    private final Entity player;

    public EndGameState(Entity player) {
        this.player = player;
    }

    @Override
    public void handle(BlackjackCore core) {
        if (!player.isAlive()) {
            core.registerEnemyGameWin();
        } else {
            player.battleReset();
            core.registerPlayerGameWin();
        }

        core.emitGameOver();
    }
}
