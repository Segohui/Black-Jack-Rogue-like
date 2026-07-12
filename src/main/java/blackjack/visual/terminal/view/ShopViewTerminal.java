package blackjack.visual.terminal.view;

import blackjack.controller.ShopController;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;
import blackjack.visual.terminal.screens.shop.ShopScreenFactory;

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
