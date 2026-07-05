package blackjack.visual.terminal.screens;

import blackjack.controller.BlackjackController;
import blackjack.visual.InputOutput;

public class RoundOverScreen implements Screen {
    private final InputOutput io;
    private final BlackjackController controller;

    public RoundOverScreen(InputOutput io, BlackjackController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printLine();
        io.printDivider("=");
        io.printUpdate("Round winner: " + controller.getWinnerName());
        io.printDivider("=");
        io.enterToProceed();
    }
}
