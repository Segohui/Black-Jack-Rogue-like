package blackjack.visual.terminal.view;

import blackjack.controller.ShopController;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.shop.ShopScreenFactory;

public class ShopViewTerminal {
    private final ShopScreenFactory screenFactory;

    public ShopViewTerminal(InputOutput io, ShopController shopController) {
        this.screenFactory = new ShopScreenFactory(io, shopController);
    }
}
