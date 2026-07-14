package blackjack.view.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.CombatOverDTO;
import blackjack.dtos.core.battle.DamageEventDTO;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

public class BattleScreenFactory {
    private final InputOutput io;
    private final BattleController controller;

    public BattleScreenFactory(InputOutput io, BattleController controller) {
        this.io = io;
        this.controller = controller;
    }

    public Screen createPlayerTurnScreen() {
        return new PlayerTurnScreen(io, controller);
    }

    public Screen createCombatOverScreen(CombatOverDTO winnerDataDTO) {
        return new CombatOverScreen(io, winnerDataDTO);
    }

    public Screen createRoundOverScreen(String winnerName) {
        return new RoundOverScreen(io, controller, winnerName);
    }

    public Screen createCardDrawScreen(CardDrawEventDTO eventData) {
        return new CardDrawScreen(io, controller, eventData);
    }

    public Screen createEntityStandScreen(String entityName) {
        return new EntityStandScreen(io, entityName);
    }

    public Screen createTakeDamageScreen(DamageEventDTO eventDTO) {
        return new TakeDamageScreen(io, eventDTO);
    }

    public Screen createActionNotificationScreen(String actionText) {
        return new ActionNotificationScreen(io, actionText);
    }
}
