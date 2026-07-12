package blackjack.visual.terminal.screens.menu;

import blackjack.controller.MenuController;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.ActionPrompter;
import blackjack.visual.terminal.screens.Screen;

public class LoseScreen implements Screen {
    private final InputOutput io;
    private final MenuController controller;

    public LoseScreen(InputOutput io, MenuController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printUpdate("You lost the game!");
        io.printMessage("\nWould you like to retry?");
        ActionPrompter actionPrompter = new ActionPrompter(io);

        actionPrompter.addAction("yes", controller::restartGame);
        actionPrompter.addAction("no", controller::selectQuit);
        actionPrompter.promptAndRun();
    }
}