package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.Entity;

public class StartRoundState implements State {
    private final Entity player;
    private final Entity enemy;

    public StartRoundState(Entity player, Entity enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void handle(BlackjackCore core) {
        player.roundReset();
        enemy.roundReset();

        player.drawInitialCards(2);
        enemy.drawInitialCards(2);

        core.activatePlayerTurnState();
    }
}
