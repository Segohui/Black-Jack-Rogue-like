package blackjack.core;

import java.util.List;

public class BlackjackGame {
    private final Signal housePlayed = new Signal();
    private final Signal gameOver = new Signal();

    private Player player;
    private Player house;
    private int playerWins = 0;
    private int houseWins = 0;
    private Player lastWinner = null;

    public void startRound() {
        player = new Player();
        house = new Player();
        house.drawCardToHand();
        house.drawCardToHand();
        player.drawCardToHand();
        player.drawCardToHand();
    }

    public void processMove() {
        int playerSum = calculatePlayerSum();
        int houseSum = calculateHouseSum();
        if (playerSum == 21 && houseSum == 21) {
            lastWinner = null;
            gameOver.emit();
        } else if (playerSum == 21 || houseSum > 21) {
            playerWins++;
            lastWinner = player;
            gameOver.emit();
        } else if (houseSum == 21 || playerSum > 21) {
            houseWins++;
            lastWinner = house;
            gameOver.emit();
        } else if (houseSum < 17) {
            house.drawCardToHand();
            houseSum = calculateHouseSum();
            if (houseSum > 21) {
                playerWins++;
                lastWinner = player;
                gameOver.emit();
                return;
            }
            housePlayed.emit();
        } else {
            housePlayed.emit();
        }
    }

    public Card playerHit() {
        Card card = player.drawCardToHand();
        processMove();
        return card;
    }

    public void playerStand() {
        processMove();
    }

    public int calculatePlayerSum() {
        return player.calculateSum();
    }

    public int calculateHouseSum() {
        return house.calculateSum();
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getHouseWins() {
        return houseWins;
    }

    public void gameOverConnect(Runnable runnable) {
        gameOver.connect(runnable);
    }

    public void housePlayedConnect(Runnable runnable) {
        housePlayed.connect(runnable);
    }

    public List<Card> getPlayerCards() {
        return player.getCards();
    }

    public List<Card> getHouseCards() {
        return house.getCards();
    }

    public String getLastWinner() {
        if (lastWinner.equals(player)) {
            return "player";
        } else if (lastWinner.equals(house)) {
            return "house";
        }
        return "tie";
    }
}
