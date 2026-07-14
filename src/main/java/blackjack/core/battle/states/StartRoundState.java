package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.entity.capabilities.ICardUser;

/**
 * Initializes a new round by resetting both entities and dealing the opening hand.
 */
public class StartRoundState implements State {
    private final ICardUser player;
    private final ICardUser enemy;

    public StartRoundState(ICardUser player, ICardUser enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void handle(BattleCore core) {
        player.roundReset();
        enemy.roundReset();

        player.drawInitialCards(2);
        enemy.drawInitialCards(2);

        core.activatePlayerTurnState();
    }
}
