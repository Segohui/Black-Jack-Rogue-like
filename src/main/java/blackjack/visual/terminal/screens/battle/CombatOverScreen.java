package blackjack.visual.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

public class CombatOverScreen implements Screen {
    private final InputOutput io;
    private final BattleController controller;

    public CombatOverScreen(InputOutput io, BattleController controller) {
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
