package blackjack.visual.terminal.screens.battle;

import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

public class XRayScreen implements Screen {
    private final InputOutput io;
    private final String cardName;

    public XRayScreen(InputOutput io, String cardName) {
        this.io = io;
        this.cardName = cardName;
    }

    @Override
    public void render() {
        io.printUpdate("Next Card: " + cardName);

        io.enterToProceed();
    }
}
