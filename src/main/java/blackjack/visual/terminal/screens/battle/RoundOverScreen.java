package blackjack.visual.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

public class RoundOverScreen implements Screen {
    private final InputOutput io;
    private final BattleController controller;

    public RoundOverScreen(InputOutput io, BattleController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printDivider("=");
        io.printUpdate("Round winner: " + controller.getWinnerName());
        io.printDivider("=");
        io.enterToProceed();
    }
}
