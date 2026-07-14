package blackjack.view.terminal.screens.menu;

import blackjack.controller.MenuController;
import blackjack.view.terminal.io.ActionPrompter;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

/**
 * Displays the defeat prompt and offers the player a retry or quit choice.
 */
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