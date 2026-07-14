package blackjack.view.terminal.screens.shop;

import blackjack.controller.ShopController;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

/**
 * Factory that creates shop screens for the terminal UI.
 */
public class ShopScreenFactory {
    private final InputOutput io;
    private final ShopController controller;

    /**
     * Creates a factory for shop screens.
     *
     * @param io terminal I/O helper
     * @param controller shop controller used by the screens
     */
    public ShopScreenFactory(InputOutput io, ShopController controller) {
        this.io = io;
        this.controller = controller;
    }

    public Screen createShopScreen() {
        return new ShopScreen(io, controller);
    }
}
