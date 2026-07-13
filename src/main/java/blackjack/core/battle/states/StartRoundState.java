package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.entity.Entity;

public class StartRoundState implements State {
    private final Entity player;
    private final Entity enemy;

    public StartRoundState(Entity player, Entity enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void handle(BattleCore core) {
        player.roundReset();
        enemy.roundReset();

        player.drawInitialCards(2);
        enemy.drawInitialCards(2);

        core.activatePlayerTurnState();
    }
}
