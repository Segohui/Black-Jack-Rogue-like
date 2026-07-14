package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.entity.capabilities.IRoundParticipant;
import blackjack.core.inventory.Inventory;

public class EndGameState implements State {
    private final IRoundParticipant player;
    private final IRoundParticipant enemy;
    private final Inventory playerInventory;
    private final Inventory enemyInventory;

    public EndGameState(IRoundParticipant player, IRoundParticipant enemy,
            Inventory playerInventory, Inventory enemyInventory) {
        this.player = player;
        this.enemy = enemy;
        this.playerInventory = playerInventory;
        this.enemyInventory = enemyInventory;
    }

    @Override
    public void handle(BattleCore core) {
        IRoundParticipant winner = (player.isAlive()) ? player : enemy;
        int goldReward = enemyInventory.getGoldAmount();

        if(player.isAlive()) {
            playerInventory.addGold(goldReward);
        }

        player.battleReset();
        core.emitCombatOverData(winner.getName(), winner.isPlayerControlled(), goldReward);
    }
}
