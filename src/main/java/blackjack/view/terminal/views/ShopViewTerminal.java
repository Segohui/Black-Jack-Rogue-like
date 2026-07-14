package blackjack.view.terminal.views;

import blackjack.controller.ShopController;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;
import blackjack.view.terminal.screens.shop.ShopScreenFactory;

/**
 * Terminal view coordinator for the shop.
 *
 * <p>Displays the shop screen when the shop controller signals entry.</p>
 */
public class ShopViewTerminal {
    private final InputOutput io;
    private final ShopScreenFactory screenFactory;

    /**
     * Creates a shop view router for the provided controller.
     *
     * @param io console I/O helper
     * @param shopController controller managing shop interactions
     */
    public ShopViewTerminal(InputOutput io, ShopController shopController) {
        this.io = io;
        this.screenFactory = new ShopScreenFactory(io, shopController);
        shopController.shopEnteredConnect(this::onShopEntered);
    }

    /**
     * Clears the terminal and renders a new screen.
     *
     * @param newScreen screen to render
     */
    private void navigateToScreen(Screen newScreen) {
        io.clearScreen();
        newScreen.render();
    }

    /**
     * Displays the shop screen when the shop is entered.
     */
    public void onShopEntered() {
        navigateToScreen(screenFactory.createShopScreen());
    }
}
