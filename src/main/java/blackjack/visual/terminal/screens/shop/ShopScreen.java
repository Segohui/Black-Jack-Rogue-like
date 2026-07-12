package blackjack.visual.terminal.screens.shop;

import java.util.List;

import blackjack.controller.ShopController;
import blackjack.visual.ConsoleColors;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.ActionPrompter;
import blackjack.visual.terminal.screens.Screen;

public class ShopScreen implements Screen {
    private final InputOutput io;
    private final ShopController controller;

    private boolean shopping = true;

    public ShopScreen(InputOutput io, ShopController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printHeader("Shop");

        while (shopping) {
            io.printColored("Gold: " + controller.getPlayerGold(), ConsoleColors.YELLOW);

            ActionPrompter actionPrompter = new ActionPrompter(io);

            List<String> items = controller.getShopItemLines();
            for (int i = 0; i < items.size(); i++) {
                int idx = i; // need this for the lambda
                actionPrompter.addAction(items.get(i), () -> buyShopItem(idx));
            }
            actionPrompter.defineBottomAction("Leave shop", () -> shopping = false);
            
            actionPrompter.promptAndRun();
        }

        controller.exitShop();
    }

    private void buyShopItem(int index) {
        boolean success = controller.buyShopItem(index);
        io.printUpdate(success ? "Purchased!" : "Not enough gold.");
    }
}