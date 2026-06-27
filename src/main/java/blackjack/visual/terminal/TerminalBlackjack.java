package blackjack.visual.terminal;

import java.util.List;

import blackjack.core.CombatRunner;
import blackjack.core.cards.Card;
import blackjack.entity.CombatEntity;
import blackjack.visual.InputOutput;

public class TerminalBlackjack {
    private final CombatRunner combatRunner;
    private final InputOutput io;

    public TerminalBlackjack(CombatEntity player, CombatRunner combatRunner, InputOutput io) {
        this.io = io;
        this.combatRunner = combatRunner;
        combatRunner.gameOverConnect(this::onGameOver);
        combatRunner.enemyPlayedConnect(this::onHousePlayed);
    }

    public void startCombat(CombatEntity enemy) {
        combatRunner.startRound(enemy);
        updateView();
        takeNextMove();
    }

    private void takeNextMove() {
        boolean validMove = false;
        while (!validMove) {
            String move = io.getInput();
            switch (move) {
                case "hit" -> {
                    combatRunner.playerHit();
                    validMove = true;
                }
                case "stand" -> {
                    combatRunner.playerStand();
                    validMove = true;
                }
            }
        }
    }

    private void onHousePlayed() {
        updateView();
        takeNextMove();
    }

    private void onGameOver() {
        updateView();
        io.printMessage("Game over!");
        io.printMessage("winner: " + combatRunner.getLastWinner());
        io.printDivider(10);
        io.printMessage("house wins count: " + combatRunner.getEnemyWins());
        io.printMessage("player wins count: " + combatRunner.getPlayerWins());
        io.printDivider(10);
        io.printMessage("");
        io.printMessage("Enter to proceed...");
        io.getInput();
    }

    private void updateView() {
        clearScreen();
        io.printDivider(20);
        List<Card> houseCards = combatRunner.getEnemyCards();
        io.printMessage("House's hand (" + combatRunner.calculateEnemySum() + "): ");
        printHand(houseCards);
        List<Card> playerCards = combatRunner.getPlayerCards();
        io.printMessage("Player's hand (" + combatRunner.calculatePlayerSum() + "): ");
        printHand(playerCards);
    }

    private void printHand(List<Card> cards) {
        for (Card card : cards) {
            System.out.print("%s %s | ".formatted(card.getRank().toString(),
                    card.getSuit().toString()));
        }
        io.printMessage("");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
