package blackjack.visual.terminal.screens;

import java.util.List;

import blackjack.controller.BlackjackController;
import blackjack.core.shop.Shop;
import blackjack.visual.InputOutput;

public class ShopScreen implements Screen {
    private final InputOutput io;
    private final BlackjackController controller;
    private final Shop shop;

    public ShopScreen(InputOutput io, BlackjackController controller, Shop shop) {
        this.io = io;
        this.controller = controller;
        this.shop = shop;
    }

    @Override
    public void render() {
        io.printHeader("Shop", 15);

        boolean shopping = true;
        while (shopping) {
            io.printMessage("Gold: " + controller.getPlayerGold());

            List<String> items = controller.getShopItemLines(shop);
            for (int i = 0; i < items.size(); i++) {
                io.printMessage((i + 1) + ") " + items.get(i));
            }
            io.printMessage("0) Leave shop");
            io.printUpdate("choose an item, or 0 to leave: ");

            String input = io.getCleanInput();

            if (input.equals("0")) {
                shopping = false;
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                boolean success = controller.buyShopItem(shop, choice - 1);
                io.printUpdate(success ? "Purchased!" : "Not enough gold or invalid item.");
            } catch (NumberFormatException e) {
                io.printUpdate("Invalid input.");
            }
        }
    }
}