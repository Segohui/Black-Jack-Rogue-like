package blackjack.view.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.dtos.core.battle.EntityStateDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.view.terminal.io.ActionPrompter;
import blackjack.view.terminal.io.ConsoleColors;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

import java.util.List;

public class PlayerTurnScreen implements Screen {
    private final InputOutput io;
    private final BattleController controller;

    public PlayerTurnScreen(InputOutput io, BattleController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printHeader("Player Turn");

        EntityStateDTO enemyData = controller.getEnemyData();
        EntityStateDTO playerData = controller.getPlayerData();

        io.printDivider("=");
        io.printEntityState(enemyData);
        io.printDivider("=");
        io.printEntityState(playerData);
        io.printDivider("=");

        ActionPrompter actionPrompter = new ActionPrompter(io);
        actionPrompter.addAction("hit", controller::playerHit);
        actionPrompter.addAction("stand", controller::playerStand);

        if (controller.playerHasItems()) {
            actionPrompter.addAction("items", this::promptItems);
        }

        actionPrompter.promptAndRun();
    }


    private String colorItemLine(ItemInfoDTO info) {

        String typeColor = switch (info.itemType()) {
            
            case CONSUMABLE -> ConsoleColors.GREEN;
            case ACTIVE -> ConsoleColors.CYAN;
            case PASSIVE -> ConsoleColors.PURPLE;

        };

        return "%s%s%s - %s".formatted(typeColor, info.name(), ConsoleColors.RESET, info.description());
    }

    private void promptItems() {
        List<ItemInfoDTO> itemInfos = controller.getItemInfos();
        io.printMessage("[PASSIVE ITEMS]");
        itemInfos.stream()
                .filter(info -> !info.isManual())
                .map(info -> "%s - %s".formatted(info.name(), info.description()))
                .forEach(line -> io.printMessage("- " + line));
        
        io.printLine();
        io.printMessage("[TRIGGERABLE ITEMS]");
        
        List<ItemInfoDTO> usableItems = itemInfos.stream()
                .filter(info -> info.isManual())
                .toList();

        ActionPrompter cardPrompter = new ActionPrompter(io);

        for (int i = 0; i < usableItems.size(); i++) {
            
            int idx = i;
            cardPrompter.addAction(colorItemLine(usableItems.get(i)), () -> controller.playerUseItem(idx));
        
        }
        
        cardPrompter.defineBottomAction("Go back", this::render);
        cardPrompter.promptAndRun();
    }
}
