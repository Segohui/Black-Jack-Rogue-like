package blackjack.core.battle.states;

import blackjack.entity.Entity;
import blackjack.core.battle.BattleCore;
import blackjack.core.cards.Card;;

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
        Card card = player.hit(); 
        core.registerCardDraw(card, player);
        core.emitDrawCard();

        int handSum = player.calculateHandSum();

        if (handSum > core.getGlobalStand()) {
            core.activateEndTurnState();
        } else {
            core.activateEnemyTurnState();
        }
    }

    @Override
    public void stand(BattleCore core) {
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
