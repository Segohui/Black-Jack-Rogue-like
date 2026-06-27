package blackjack.visual.terminal;

import java.util.List;
import java.util.Scanner;

import blackjack.core.BlackjackGame;
import blackjack.core.Card;

public class TerminalBlackjack {
    private final BlackjackGame blackjackGame = new BlackjackGame();

    public TerminalBlackjack() {
        blackjackGame.gameOverConnect(this::onGameOver);
        blackjackGame.housePlayedConnect(this::onHousePlayed);
    }

    public void startRound() {
        blackjackGame.startRound();
        updateView();
        takeNextMove();
    }

    private void takeNextMove() {
        try (Scanner sc = new Scanner(System.in)) {
            boolean validMove = false;
            while (!validMove) {
                // TODO: fix this throwin an error when restarting round for whatever reason
                String move = sc.nextLine().strip().toLowerCase();
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
    }

    private void onHousePlayed() {
        updateView();
        takeNextMove();
    }

    private void onGameOver() {
        updateView();
        System.out.println("Game over!");
        System.out.println("winner: " + blackjackGame.getLastWinner());
        System.out.println("-----------------");
        System.out.println("house wins count: " + blackjackGame.getHouseWins());
        System.out.println("player wins count: " + blackjackGame.getPlayerWins());
        System.out.println("-----------------");
        System.out.println();
        System.out.println("Enter to proceed...");
        try (Scanner sc = new Scanner(System.in)) {
            sc.nextLine();
        }
        startRound();
    }

    private void updateView() {
        clearScreen();
        System.out.println("-----------------------------");
        List<Card> houseCards = blackjackGame.getHouseCards();
        System.out.print("House hand (%d): ".formatted(blackjackGame.calculateHouseSum()));
        printHand(houseCards);
        List<Card> playerCards = blackjackGame.getPlayerCards();
        System.out.print("Player hand (%d): ".formatted(blackjackGame.calculatePlayerSum()));
        printHand(playerCards);
    }

    private void printHand(List<Card> cards) {
        for (Card card : cards) {
            System.out.print("%s %s | ".formatted(card.getRank().toString(),
                    card.getSuit().toString()));
        }
        System.out.println();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
