package blackjack.visual.terminal;

import blackjack.controller.BlackjackController;
import blackjack.dto.CombatResultData;
import blackjack.dto.EntityStateData;
import blackjack.visual.InputOutput;

public class BlackjackViewTerminal {
    private final InputOutput io;
    private final BlackjackController controller;

    public BlackjackViewTerminal(InputOutput io, BlackjackController controller) {
        this.io = io;
        this.controller = controller;
        controller.gameOverConnect(this::onGameOver);
        controller.nextTurnConnect(this::takePlayerTurn);
    }

    public String readPlayerInput() {
        io.printMessage("type 'hit' or 'stand': ");
        return io.getInput().strip().toLowerCase();
    }

    public void entitiesHandsScreen(EntityStateData enemyData, EntityStateData playerData) {
        io.printDivider(30);
        
        io.printMessage(enemyData.name() + " 's hand (" + enemyData.currentSum() + "): ");
        io.printHand(enemyData.cardNames()); 
        
        io.printMessage(playerData.name() + " 's hand (" + playerData.currentSum() + "): ");
        io.printHand(playerData.cardNames());
    }

    public void gameEndScreen(CombatResultData result) {
        io.printMessage("Game over!");
        io.printMessage("winner: " + result.winnerName());
        io.printDivider(15);
        io.printMessage("win count: " + result.playerWins());
        io.printMessage("lost count: " + result.playerLosses());
        io.printDivider(15);
        io.printMessage("");
        io.printMessage("Enter to proceed...");
        io.getInput();
    }

    public void takePlayerTurn() {
        while (true) {
            entitiesHandsScreen(controller.getEnemyData(), controller.getPlayerData());
            String input = readPlayerInput();
            if (input.equals("hit")) {
                controller.playerHit();
            } else if (input.equals("stand")) {
                controller.playerStand();
            } else {
                continue;
            }
            
            break;
        }
    }

    private void onGameOver() {
        entitiesHandsScreen(controller.getEnemyData(), controller.getPlayerData());
        gameEndScreen(controller.getCombatResult());
    }
}