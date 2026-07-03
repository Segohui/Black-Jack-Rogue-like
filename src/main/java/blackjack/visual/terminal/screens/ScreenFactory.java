package blackjack.visual.terminal.screens;

import blackjack.controller.BlackjackController;
import blackjack.visual.InputOutput;

public class ScreenFactory {
    private final InputOutput io;
    private final BlackjackController controller;

    public ScreenFactory(InputOutput io, BlackjackController controller) {
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
