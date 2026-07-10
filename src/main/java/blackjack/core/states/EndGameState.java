package blackjack.core.states;

import blackjack.core.BattleCore;
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
        core.registerBattleWinner(winner);

        if(player.isAlive()) {
            player.addGold(GOLD_REWARD);
            core.registerGoldReward(GOLD_REWARD);
        }
        player.battleReset();
        core.emitCombatOver(player.isAlive());
    }
}
