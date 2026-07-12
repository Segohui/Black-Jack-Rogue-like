package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.inventory.Inventory;
import blackjack.entity.Entity;

public class EndGameState implements State {
    private final Entity player;
    private final Entity enemy;
    private final Inventory playerInventory;
    private final Inventory enemyInventory;

    public EndGameState(Entity player, Entity enemy,
            Inventory playerInventory, Inventory enemyInventory) {
        this.player = player;
        this.enemy = enemy;
        this.playerInventory = playerInventory;
        this.enemyInventory = enemyInventory;
    }

    @Override
    public void handle(BattleCore core) {
        Entity winner = (player.isAlive()) ? player : enemy;
        int goldReward = enemyInventory.getGoldAmount();

        if(player.isAlive()) {
            playerInventory.addGold(goldReward);
        }

        player.battleReset();
        core.emitCombatOverData(winner.getName(), winner.isPlayerControlled(), goldReward);
    }
}
