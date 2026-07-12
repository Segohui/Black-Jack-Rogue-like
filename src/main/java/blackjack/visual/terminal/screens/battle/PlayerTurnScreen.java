package blackjack.visual.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.dto.EntityStateDTO;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.ActionPrompter;
import blackjack.visual.terminal.screens.Screen;

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
            actionPrompter.addAction("use item", this::promptPurchasedCard);
        }

        actionPrompter.promptAndRun();
    }

    private void promptPurchasedCard() {
        List<String> itemLines = controller.getItemLines();
        ActionPrompter cardPrompter = new ActionPrompter(io);

        for (int i = 0; i < itemLines.size(); i++) {
            int idx = i;
            cardPrompter.addAction(itemLines.get(i), () -> controller.playerUseItem(idx));
        }
        cardPrompter.defineBottomAction("Go back", this::render);
        cardPrompter.promptAndRun();
    }
}
