package blackjack.visual.terminal;

import blackjack.controller.BlackjackController;
import blackjack.dto.DamageEventData;
import blackjack.dto.EntityStateData;
import blackjack.visual.InputOutput;

public class BlackjackViewTerminal {
    private final InputOutput io;
    private final BlackjackController controller;

    public BlackjackViewTerminal(InputOutput io, BlackjackController controller) {
        this.io = io;
        this.controller = controller;
        controller.roundOverConnect(this::onRoundOver);
        controller.nextTurnConnect(this::takePlayerTurn);
        controller.takeDamageConnect(this::onTakeDamage);
        controller.gameOverConnect(this::onGameOver);
        controller.enemyStandConnect(this::enemyStand);
    }

    public String readPlayerInput() {
        io.printUpdate("type 'hit' or 'stand': ");
        return io.getInput().strip().toLowerCase();
    }

    public void entitiesHandsScreen(EntityStateData enemyData, EntityStateData playerData) {
        io.printDivider(15);
        io.printMessage(enemyData.name() + " 's hand (" + enemyData.currentSum() + "): ");
        io.printMessage("Hp: " + enemyData.hp());
        io.printHand(enemyData.cardNames()); 
        io.printDivider(15);
        io.printMessage(playerData.name() + " 's hand (" + playerData.currentSum() + "): ");
        io.printMessage("Hp: " + playerData.hp());
        io.printHand(playerData.cardNames());
        io.printDivider(15);
    }

    public void roundOverScreen(String winnerName) {
        io.printLine();
        io.printUpdate("Round winner: " + winnerName);
        io.printDivider(15);
        io.enterToProceed();
    }

    public void gameOverScreen(String winnerName) {
        io.printLine();
        io.printHeader("Game End", 15);
        io.printUpdate("Winner: " + winnerName);
        io.printUpdate("Money gained: ");
        io.enterToProceed();
    }

    public void takeDamageScreen(DamageEventData damageEventData) {
        handRefresh();
        io.printUpdate(damageEventData.targetName() + " took " + damageEventData.damage() + " points of damage");
    }

    public void takePlayerTurn() {
        while (true) {
            handRefresh();
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

    public void enemyStand() {
        io.printLine();
        io.printUpdate("Enemy Stand");
        io.enterToProceed();
    }

    private void onRoundOver() {
        roundOverScreen(controller.getWinnerName());
    }

    private void onGameOver() {
        gameOverScreen(controller.getWinnerName());
    }

    private void onTakeDamage() {
        takeDamageScreen(controller.getDamageEvent());
    }

    private void handRefresh() {
        entitiesHandsScreen(controller.getEnemyData(), controller.getPlayerData());
    }
}