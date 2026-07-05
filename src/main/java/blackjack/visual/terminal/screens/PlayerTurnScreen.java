package blackjack.visual.terminal.screens;

import blackjack.controller.BlackjackController;
import blackjack.dto.EntityStateData;
import blackjack.visual.InputOutput;

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

        io.printUpdate("type 'hit' or 'stand': ");
        while (true) {
            switch (io.getCleanInput()) {
                case "hit" -> {
                    controller.playerHit();
                    return;
                }
                case "stand" -> {
                    controller.playerStand();
                    return;
                }
                case "clear" -> {
                    io.clearScreen();
                    return;
                }
                default ->  {
                    io.printUpdate("Invalid input. Please type 'hit' or 'stand': ");
                }
            }
        }
        
    }
}
