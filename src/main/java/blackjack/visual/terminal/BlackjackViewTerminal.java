package blackjack.visual.terminal;

import blackjack.dto.CombatResultData;
import blackjack.dto.EntityStateData;
import blackjack.visual.InputOutput;

public class BlackjackViewTerminal {
    private final InputOutput io;

    public BlackjackViewTerminal(InputOutput io) {
        this.io = io;
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
        io.printMessage("lost count: " + result.playerLoses());
        io.printDivider(15);
        io.printMessage("");
        io.printMessage("Enter to proceed...");
        io.getInput();
    }
}