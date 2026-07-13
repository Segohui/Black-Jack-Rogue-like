package blackjack.view.screens.battle;

import blackjack.controller.BattleController;
import blackjack.dtos.core.battle.EntityStateDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.view.ActionPrompter;
import blackjack.view.InputOutput;
import blackjack.view.screens.Screen;

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

    private void promptItems() {
        List<ItemInfoDTO> itemInfos = controller.getItemInfos();
        io.printMessage("[PASSIVE ITEMS]");
        itemInfos.stream()
                .filter(info -> !info.isManual())
                .map(info -> "%s - %s".formatted(info.name(), info.description()))
                .forEach(line -> io.printMessage("- " + line));
        
        io.printLine();
        io.printMessage("[TRIGGERABLE ITEMS]");
        
        List<String> usableItemLines = itemInfos.stream()
                .filter(info -> info.isManual())
                .map(info -> "%s - %s".formatted(info.name(), info.description()))
                .toList();
        ActionPrompter cardPrompter = new ActionPrompter(io);

        for (int i = 0; i < usableItemLines.size(); i++) {
            int idx = i;
            cardPrompter.addAction(usableItemLines.get(i), () -> controller.playerUseItem(idx));
        }
        cardPrompter.defineBottomAction("Go back", this::render);
        cardPrompter.promptAndRun();
    }
}
