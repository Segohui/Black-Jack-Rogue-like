package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.Entity;

public class EndGameState implements State {
    private final Entity player;
    private final Entity enemy;

    public EndGameState(Entity player, Entity enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void handle(BlackjackCore core) {
        Entity winner = (player.isAlive()) ? player : enemy;
        core.registerBattleWinner(winner);
        player.battleReset();

        core.emitCombatOver();
    }
}
