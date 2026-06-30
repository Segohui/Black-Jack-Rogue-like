package blackjack.visual.terminal;

import blackjack.controller.BlackjackController;
import blackjack.dto.CardDrawEventData;
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
        controller.drawCardConnect(this::onDrawCard);
    }

    public String readPlayerInput() {
        io.printUpdate("type 'hit' or 'stand': ");
        return io.getInput().strip().toLowerCase();
    }

    public void entityHandScreen(EntityStateData entityData) {
        io.printMessage(entityData.name() + " 's hand (" + entityData.currentSum() + "): ");
        io.printMessage("Hp: " + entityData.hp());
        io.printHand(entityData.cardNames());
    }

    public void roundOverScreen(String winnerName) {
        io.printLine();
        io.printUpdate("Round winner: " + winnerName);
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

    public void drawCardScreen(CardDrawEventData cardDraw) {
        io.printUpdate(cardDraw.entityName() + " drew: " + cardDraw.cardName());
        io.enterToProceed();
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

    private void onDrawCard() {
        drawCardScreen(controller.getDrawnCardEvent());
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
        io.printDivider();
        entityHandScreen(controller.getEnemyData());
        io.printDivider();
        entityHandScreen(controller.getPlayerData());
        io.printDivider();
    }
}