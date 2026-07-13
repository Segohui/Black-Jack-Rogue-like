package blackjack.view.screens.battle;

import blackjack.view.InputOutput;
import blackjack.view.screens.Screen;

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
