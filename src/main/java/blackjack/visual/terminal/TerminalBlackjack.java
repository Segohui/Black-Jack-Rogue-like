package blackjack.visual.terminal;

import java.util.List;

import blackjack.core.CombatRunner;
import blackjack.core.cards.Card;
import blackjack.entity.Enemy;
import blackjack.entity.Player;
import blackjack.visual.InputOutput;

public class TerminalBlackjack {
    private final CombatRunner combatRunner;
    private final InputOutput io;

    public TerminalBlackjack(InputOutput io, Player player) {
        this.io = io;
        this.combatRunner = new CombatRunner(player);
        combatRunner.gameOverConnect(this::onGameOver);
        combatRunner.nextTurnConnect(this::nextTurn);
    }

    public void runCombat(Player player, Enemy enemy) {
        combatRunner.startRound(enemy);
        updateView();
        takePlayerTurn();
    }

    private void takePlayerTurn() {
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

    private void nextTurn() {
        updateView();
        takePlayerTurn();
    }

    private void onGameOver() {
        updateView();
        io.printMessage("Game over!");
        io.printMessage("winner: " + combatRunner.getLastWinnerName());
        io.printDivider(15);
        io.printMessage("win count: " + combatRunner.getPlayerWins());
        io.printMessage("lost count: " + combatRunner.getEnemyWins());
        io.printDivider(15);
        io.printMessage("");
        io.printMessage("Enter to proceed...");
        io.getInput();
    }

    private void updateView() {
        io.printDivider(30);
        List<Card> houseCards = combatRunner.getEnemyCards();
        io.printMessage(combatRunner.getEnemyName() + " 's hand (" + combatRunner.calculateEnemySum() + "): ");
        printHand(houseCards);
        List<Card> playerCards = combatRunner.getPlayerCards();
        io.printMessage(combatRunner.getPlayerName() + " 's hand (" + combatRunner.calculatePlayerSum() + "): ");
        printHand(playerCards);
    }

    private void printHand(List<Card> cards) {
        for (Card card : cards) {
            System.out.print("%s %s | ".formatted(card.getRank().toString(),
                    card.getSuit().toString()));
        }
        io.printMessage("");
    }
}
