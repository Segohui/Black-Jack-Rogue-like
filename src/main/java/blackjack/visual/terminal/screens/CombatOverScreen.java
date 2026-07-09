package blackjack.visual.terminal.screens;

import blackjack.controller.BlackjackController;
import blackjack.visual.InputOutput;

public class CombatOverScreen implements Screen {
    private final InputOutput io;
    private final BlackjackController controller;

    public CombatOverScreen(InputOutput io, BlackjackController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printLine();
        io.printHeader("Game End", 15);
        io.printUpdate("Winner: " + controller.getWinnerName());
        io.printUpdate("Money gained: " + controller.getLastGoldReward());
        io.enterToProceed();
    }
}
