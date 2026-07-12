package blackjack.visual.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.dto.CardDrawEventDTO;
import blackjack.dto.CombatOverDTO;
import blackjack.dto.DamageEventDTO;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

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
}
