package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.entity.Entity;

public class EndGameState implements State {
    private final Entity player;
    private final Entity enemy;

    public EndGameState(Entity player, Entity enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void handle(BattleCore core) {
        Entity winner = (player.isAlive()) ? player : enemy;
        int goldReward = enemy.getGold();

        if(player.isAlive()) {
            player.addGold(goldReward);
        }

        player.battleReset();
        core.emitCombatOverData(winner.getName(), winner.isPlayerControlled(), goldReward);
    }
}
