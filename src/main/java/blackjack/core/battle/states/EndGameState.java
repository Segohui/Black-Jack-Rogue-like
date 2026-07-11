package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.entity.Entity;

public class EndGameState implements State {
    private static final int GOLD_REWARD = 25;
    private final Entity player;
    private final Entity enemy;

    public EndGameState(Entity player, Entity enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void handle(BattleCore core) {
        Entity winner = (player.isAlive()) ? player : enemy;

        if(player.isAlive()) {
            player.addGold(GOLD_REWARD);
        }

        player.battleReset();
        core.emitCombatOverData(winner.getName(), winner.isPlayerControlled(), GOLD_REWARD);
    }
}
