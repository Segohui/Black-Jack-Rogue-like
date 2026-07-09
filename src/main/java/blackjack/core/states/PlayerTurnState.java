package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.entity.Entity;
import blackjack.core.cards.Card;;

public class PlayerTurnState implements State {
    private final Entity player;

    public PlayerTurnState(Entity player) {
        this.player = player;
    }

    @Override
    public void handle(BlackjackCore core) {
        core.emitPlayerTurn();
    }

    @Override
    public void hit(BlackjackCore core) {
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
    public void stand(BlackjackCore core) {
        core.activateEnemyOnlyTurnState();
    }

    @Override
    public void useBoughtCard(BlackjackCore core, int idx){
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
