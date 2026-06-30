package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.core.states.helper.DrawCardHelper;
import blackjack.entity.components.BehaviorComponent;
import blackjack.entity.components.DeckComponent;

public class EnemyOnlyTurnState implements State {
    private final DeckComponent deckComponent;
    private final BehaviorComponent behaviorComponent;
    private final DrawCardHelper drawCardHelper = new DrawCardHelper();

    public EnemyOnlyTurnState(DeckComponent deckComponent, BehaviorComponent behaviorComponent) {
        this.deckComponent = deckComponent;
        this.behaviorComponent = behaviorComponent;
    }

    public void handle(BlackjackCore core) {
        int handSum = deckComponent.calculateHandSum();
        int globalStand = core.getGlobalStand();
        int enemyLimit = behaviorComponent.calculateStandValue(globalStand);

        if (handSum < enemyLimit) {
            drawCardHelper.enemyDrawCard(deckComponent, core, 1);
            handSum = deckComponent.calculateHandSum();

            core.activateEnemyOnlyTurnState();
        }
        
        if (handSum <= globalStand || handSum >= enemyLimit) {
            core.emitEnemyStand();
        }
        
        core.activateEndTurnState();
    }
}
