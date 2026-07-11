package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.entity.Entity;

public class PlayerOnlyState implements State {
    private final Entity player;

    public PlayerOnlyState(Entity player) {
        this.player = player;
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
