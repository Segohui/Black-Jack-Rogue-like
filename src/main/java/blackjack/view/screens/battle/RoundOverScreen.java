package blackjack.view.screens.battle;

import blackjack.controller.BattleController;
import blackjack.view.InputOutput;
import blackjack.view.screens.Screen;

public class RoundOverScreen implements Screen {
    private final InputOutput io;
    private final BattleController controller;
    private final String winnerName;

    public RoundOverScreen(InputOutput io, BattleController controller, String winnerName) {
        this.io = io;
        this.controller = controller;
        this.winnerName = winnerName;
    }

    @Override
    public void render() {
        if (winnerName == null) {
            io.printUpdate("No winner (tie)");
        } else {
            io.printUpdate("Round Winner: " + winnerName);
        }

        io.enterToProceed();
    }
}
