package blackjack.core.battle.states;

import blackjack.entity.Entity;
import blackjack.core.battle.BattleCore;
import blackjack.core.inventory.Inventory;

public class PlayerTurnState implements State {
    private final Entity player;
    private final Inventory playerInventory;

    public PlayerTurnState(Entity player, Inventory playerInventory) {
        this.player = player;
        this.playerInventory = playerInventory;
    }

    @Override
    public void handle(BattleCore core) {
        core.emitPlayerTurn();
    }

    @Override
    public void hit(BattleCore core) {
        player.hit();

        int handSum = player.calculateHandSum();

        if (handSum > core.getGlobalStand()) {
            core.activateEndTurnState();
        } else {
            core.activateEnemyTurnState();
        }
    }

    @Override
    public void stand(BattleCore core) {
        player.stand();
        
        core.activateEnemyOnlyTurnState();
    }

    @Override
    public void useItem(BattleCore core, int idx){
        playerInventory.triggerItem(idx, core.getBattleContextDTO());

        int handSum = player.calculateHandSum();

        if(handSum > core.getGlobalStand()){
            core.activateEndTurnState();
        }
        else{
            core.activateEnemyTurnState();
        }
    }
}
