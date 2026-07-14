package blackjack.view.terminal.screens.menu;

import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

/**
 * Displays the quit message before the application exits.
 */
public class QuitScreen implements Screen {
    private final InputOutput io;

    public QuitScreen(InputOutput io) {
        this.io = io;
    }

    @Override
    public void render() {
        io.printMessage("Quiting the game... ");
        io.enterToProceed();
    }
}