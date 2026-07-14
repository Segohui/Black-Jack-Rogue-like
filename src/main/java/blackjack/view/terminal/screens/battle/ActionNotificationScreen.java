package blackjack.view.terminal.screens.battle;

import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

public class ActionNotificationScreen implements Screen {
    private final InputOutput io;
    private final String actionText;

    public ActionNotificationScreen(InputOutput io, String actionText) {
        this.io = io;
        this.actionText = actionText;
    }

    @Override
    public void render() {
        io.printUpdate(actionText);

        io.enterToProceed();
    }
}
