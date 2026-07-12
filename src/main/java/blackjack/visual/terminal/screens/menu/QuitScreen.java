package blackjack.visual.terminal.screens.menu;

import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

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