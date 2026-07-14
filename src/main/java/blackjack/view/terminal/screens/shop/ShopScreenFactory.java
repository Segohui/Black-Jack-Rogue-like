package blackjack.view.terminal.screens.shop;

import blackjack.controller.ShopController;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

public class ShopScreenFactory {
    private final InputOutput io;
    private final ShopController controller;

    public ShopScreenFactory(InputOutput io, ShopController controller) {
        this.io = io;
        this.controller = controller;
    }

    public Screen createShopScreen() {
        return new ShopScreen(io, controller);
    }
}
