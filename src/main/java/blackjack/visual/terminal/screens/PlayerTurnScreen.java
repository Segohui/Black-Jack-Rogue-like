package blackjack.visual.terminal.screens;

import blackjack.controller.BlackjackController;
import blackjack.dto.EntityStateData;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.ActionPrompter;

public class PlayerTurnScreen implements Screen {
    private final InputOutput io;
    private final BlackjackController controller;

    public PlayerTurnScreen(InputOutput io, BlackjackController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printHeader("Player Turn", 15);

        EntityStateData enemyData = controller.getEnemyData();
        EntityStateData playerData = controller.getPlayerData();

        io.printDivider("=");
        io.printEntityState(playerData);
        io.printDivider("=");
        io.printEntityState(enemyData);
        io.printDivider("=");

        ActionPrompter actionPrompter = new ActionPrompter(io);
        actionPrompter.addAction("hit", controller::playerHit);
        actionPrompter.addAction("stand", controller::playerStand);
        actionPrompter.promptAndRun();
    }
}
