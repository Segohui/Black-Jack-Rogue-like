package blackjack.visual.terminal;

import blackjack.visual.InputOutput;

public class NotificationRenderer {
    private final InputOutput io;

    public NotificationRenderer(InputOutput io) {
        this.io = io;
    }

    public void showPopup(String message) {
        io.printLine();
        io.printUpdate(message);
        io.enterToProceed();
    }
}