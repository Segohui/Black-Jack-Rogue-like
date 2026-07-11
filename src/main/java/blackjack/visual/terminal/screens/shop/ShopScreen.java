package blackjack.visual.terminal.screens.shop;

import java.util.List;

import blackjack.controller.ShopController;
import blackjack.core.shop.Shop;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.ActionPrompter;
import blackjack.visual.terminal.screens.Screen;

public class ShopScreen implements Screen {
    private final InputOutput io;
    private final ShopController controller;
    private final Shop shop;

    private boolean shopping = true;

    public ShopScreen(InputOutput io, ShopController controller, Shop shop) {
        this.io = io;
        this.controller = controller;
        this.shop = shop;
    }

    @Override
    public void render() {
        io.printHeader("Shop");

        while (shopping) {
            io.printMessage("Gold: " + controller.getPlayerGold());

            ActionPrompter actionPrompter = new ActionPrompter(io);

            List<String> items = controller.getShopItemLines(shop);
            for (int i = 0; i < items.size(); i++) {
                int idx = i; // need this for the lambda
                actionPrompter.addAction(items.get(i), () -> buyShopItem(shop, idx));
            }
            actionPrompter.defineBottomAction("Leave shop", () -> shopping = false);
            
            actionPrompter.promptAndRun();
        }
    }

    private void buyShopItem(Shop shop, int index) {
        boolean success = controller.buyShopItem(shop, index);
        io.printUpdate(success ? "Purchased!" : "Not enough gold.");
    }
}