package blackjack.core.battle.states;

import blackjack.entity.Entity;
import blackjack.core.battle.BattleCore;

public class PlayerTurnState implements State {
    private final Entity player;

    public PlayerTurnState(Entity player) {
        this.player = player;
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
    public void useBoughtCard(BattleCore core, int idx){
        player.usePurchasedCard(idx);

        int handSum = player.calculateHandSum();

        if(handSum > core.getGlobalStand()){
            core.activateEndTurnState();
        }
        else{
            core.activateEnemyTurnState();
        }
    }
}
