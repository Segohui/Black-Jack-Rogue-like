package blackjack.visual.terminal;

import java.util.List;

import blackjack.core.CombatRunner;
import blackjack.core.cards.Card;
import blackjack.visual.InputOutput;

public class TerminalBlackjack {
    private final CombatRunner blackjackGame = new CombatRunner();
    private final InputOutput io;

    public TerminalBlackjack(InputOutput io) {
        this.io = io;
        blackjackGame.gameOverConnect(this::onGameOver);
        blackjackGame.housePlayedConnect(this::onHousePlayed);
    }

    public void startRound() {
        blackjackGame.startRound();
        updateView();
        takeNextMove();
    }

    private void takeNextMove() {
        boolean validMove = false;
        while (!validMove) {
            String move = io.getInput();
            switch (move) {
                case "hit" -> {
                    blackjackGame.playerHit();
                    validMove = true;
                }
                case "stand" -> {
                    blackjackGame.playerStand();
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
        io.printMessage("winner: " + blackjackGame.getLastWinner());
        io.printMessage("-----------------");
        io.printMessage("house wins count: " + blackjackGame.getHouseWins());
        io.printMessage("player wins count: " + blackjackGame.getPlayerWins());
        io.printMessage("-----------------");
        io.printMessage("");
        io.printMessage("Enter to proceed...");
        io.getInput();

        startRound();
    }

    private void updateView() {
        clearScreen();
        io.printMessage("-----------------------------");
        List<Card> houseCards = blackjackGame.getHouseCards();
        io.printMessage("House's hand (" + blackjackGame.calculateHouseSum() + "): ");
        printHand(houseCards);
        List<Card> playerCards = blackjackGame.getPlayerCards();
        io.printMessage("Player's hand (" + blackjackGame.calculatePlayerSum() + "): ");
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
