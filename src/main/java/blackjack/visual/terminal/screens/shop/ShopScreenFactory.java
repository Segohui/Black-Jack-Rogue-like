package blackjack.visual.terminal.screens.shop;

import blackjack.controller.ShopController;
import blackjack.core.shop.Shop;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

public class ShopScreenFactory {
    private final InputOutput io;
    private final ShopController controller;

    public ShopScreenFactory(InputOutput io, ShopController controller) {
        this.io = io;
        this.controller = controller;
    }

    public Screen createShopScreen(Shop shop) {
        return new ShopScreen(io, controller, shop);
    }
}
