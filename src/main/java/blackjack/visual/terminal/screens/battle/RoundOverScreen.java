package blackjack.visual.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.visual.ConsoleColors;
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
        String message = "Round Winner: " + controller.getWinnerName();

        io.printDivider("=", message.length());
        io.printColored("Round winner: " + controller.getWinnerName(), ConsoleColors.YELLOW);
        io.printDivider("=", message.length());
        io.enterToProceed();
    }
}
