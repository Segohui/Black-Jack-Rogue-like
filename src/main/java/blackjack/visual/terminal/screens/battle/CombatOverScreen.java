package blackjack.visual.terminal.screens.battle;

import java.util.ArrayList;
import java.util.List;

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
        List<String> messages = new ArrayList<>();

        messages.add("Winner: " + controller.getWinnerName());
        messages.add("Money gained: " + controller.getLastGoldReward());

        io.printLine();
        io.printHeader("Game End");
        io.printUpdate(messages, 20);
        io.enterToProceed();
    }
}
