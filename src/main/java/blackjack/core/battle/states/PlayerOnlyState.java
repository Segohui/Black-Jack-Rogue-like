package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.inventory.Inventory;
import blackjack.entity.Entity;

public class PlayerOnlyState implements State {
    private final Entity player;
    private final Inventory playerInventory;

    public PlayerOnlyState(Entity player, Inventory playerInventory) {
        this.player = player;
        this.playerInventory = playerInventory;
    }

    @Override
    public void handle(BattleCore core) {
        int handSum = player.calculateHandSum();

        if (handSum > core.getGlobalStand()) {
            core.activateEndTurnState();
        }

        core.emitPlayerTurn();
    }

    @Override
    public void hit(BattleCore core) {
        player.hit();

        int handSum = player.calculateHandSum();

        if (handSum > core.getGlobalStand()) {
            core.activateEndTurnState();
        } else {
            core.activatePlayerOnlyTurnState();
        }
    }

    @Override
    public void stand(BattleCore core) {
        player.stand();
        core.activateEndTurnState();
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
