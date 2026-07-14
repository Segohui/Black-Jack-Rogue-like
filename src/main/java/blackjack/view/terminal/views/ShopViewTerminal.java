package blackjack.view.terminal.views;

import blackjack.controller.ShopController;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;
import blackjack.view.terminal.screens.shop.ShopScreenFactory;

public class ShopViewTerminal {
    private final InputOutput io;
    private final ShopScreenFactory screenFactory;

    public ShopViewTerminal(InputOutput io, ShopController shopController) {
        this.io = io;
        this.screenFactory = new ShopScreenFactory(io, shopController);
        shopController.shopEnteredConnect(this::onShopEntered);
    }

    private void navigateToScreen(Screen newScreen) {
        io.clearScreen();
        newScreen.render();
    }

    public void onShopEntered() {
        navigateToScreen(screenFactory.createShopScreen());
    }
}
