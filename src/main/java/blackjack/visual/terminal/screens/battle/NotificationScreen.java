package blackjack.visual.terminal.screens.battle;

import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

public class NotificationScreen implements Screen {
    private final InputOutput io;
    private final String message;

    public NotificationScreen(InputOutput io, String message) {
        this.io = io;
        this.message = message;
    }

    public void render() {
        io.printLine();
        io.printUpdate(message);
        io.enterToProceed();
    }
}