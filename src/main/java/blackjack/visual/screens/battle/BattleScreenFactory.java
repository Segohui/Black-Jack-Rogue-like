package blackjack.visual.screens.battle;

import blackjack.controller.BattleController;
import blackjack.visual.InputOutput;
import blackjack.visual.screens.Screen;

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

    public Screen createCombatOverScreen() {
        return new CombatOverScreen(io, controller);
    }

    public Screen createRoundOverScreen() {
        return new RoundOverScreen(io, controller);
    }

    public Screen createCardDrawScreen() {
        return new CardDrawScreen(io, controller);
    }
}
