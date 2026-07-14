package blackjack.view.terminal.screens.shop;

import java.util.List;

import blackjack.controller.ShopController;
import blackjack.dtos.core.items.ShopItemDTO;
import blackjack.exceptions.InsufficientGoldException;
import blackjack.view.terminal.io.ActionPrompter;
import blackjack.view.terminal.io.ConsoleColors;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

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
            io.printColored("\nGold: " + controller.getPlayerGold(), ConsoleColors.YELLOW);
            
            io.printMessage("Types: " + 
                ConsoleColors.GREEN + "Cosumable" + ConsoleColors.RESET + " | " +
                ConsoleColors.CYAN + "Active" + ConsoleColors.RESET + " | " +
                ConsoleColors.PURPLE + "Passive" + ConsoleColors.RESET);
            io.printLine();

            ActionPrompter actionPrompter = new ActionPrompter(io);

            List<ShopItemDTO> items = controller.getShopItems();
            
            for (int i = 0; i < items.size(); i++) {
                int idx = i; 
                ShopItemDTO item = items.get(i);
                
                String typeColor = switch (item.itemType()) {
                    case CONSUMABLE -> ConsoleColors.GREEN;
                    case ACTIVE -> ConsoleColors.CYAN;
                    case PASSIVE -> ConsoleColors.PURPLE;
                };

                String coloredOption = String.format("%s%s%s - %s%dg%s - %s",
                        typeColor, item.name(), ConsoleColors.RESET,
                        ConsoleColors.YELLOW, item.price(), ConsoleColors.RESET,
                        item.description());

                actionPrompter.addAction(coloredOption, () -> buyShopItem(idx));
            }
            actionPrompter.defineBottomAction("Leave shop", () -> shopping = false);
            
            actionPrompter.promptAndRun();
        }

        controller.exitShop();
    }

    private void buyShopItem(int index) {
        try {
            boolean success = controller.buyShopItem(index);
            io.printUpdate(success ? "Purchased!" : "Invalid Item.");

        } catch (InsufficientGoldException e) {
            io.printUpdate(e.getMessage());
        }    
    }
}