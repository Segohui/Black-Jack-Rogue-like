package blackjack.core;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.entity.CombatEntity;
import blackjack.entity.Enemy;
import blackjack.entity.Player;

public class CombatRunner {
    // maybe initialize observers outside of combatRunner
    private final Signal nextTurn = new Signal();
    private final Signal gameOver = new Signal();

    private final Player player;
    private Enemy enemy;
    private int playerWins = 0;
    private int enemyWins = 0;
    private CombatEntity lastWinner;

    public CombatRunner(Player player) {
        this.player = player;
    }

    public void processMove(boolean playerStand) {
        int playerSum = calculatePlayerSum();

        if (playerSum > 21) {
            endGame(enemy);
            return;
        }

        if (playerSum == 21) {
            playerStand = true;
        }

        int enemySum = calculateEnemySum();

        if (enemySum < enemy.getStandValue()) {
            enemy.drawCardToHand();
            enemySum = calculateEnemySum();
            if (enemySum > 21) {
                endGame(player);
                return;
            }
            nextTurn.emit();
        }

        if (enemySum > 21) {
            endGame(player);
            return;
        }

        if (playerStand && enemySum >= enemy.getStandValue()) {
            evaluateWinner(playerSum, enemySum);
            return;
        }
        else {
            nextTurn.emit();
        }
    }

    private void evaluateWinner(int playerSum, int enemySum) {
        if (playerSum > enemySum) {
            endGame(player);
        } else if (enemySum > playerSum) {
            endGame(enemy);
        } else {
            endGame(null);
        }
    }

    private void endGame(CombatEntity winner) {
        if (winner == null) {
            lastWinner = null;
        } else if (winner.isPlayerControlled()) {
            playerWins++;
            lastWinner = player;
        } else if (!winner.isPlayerControlled()) {
            enemyWins++;
            lastWinner = enemy;
        }

        gameOver.emit();
    }

    public void startRound(Enemy enemy) {
        this.enemy = enemy; 
        lastWinner = null;
        enemy.drawCardToHand();
        enemy.drawCardToHand();
        player.drawCardToHand();
        player.drawCardToHand();
    }

    public Card playerHit() {
        Card card = player.drawCardToHand();
        processMove(false);
        return card;
    }

    public void playerStand() {
        processMove(true);
    }

    public int calculatePlayerSum() {
        return player.calculateSum();
    }

    public int calculateEnemySum() {
        return enemy.calculateSum();
    }

    public void gameOverConnect(Runnable runnable) {
        gameOver.connect(runnable);
    }

    public void nextTurnConnect(Runnable runnable) {
        nextTurn.connect(runnable);
    }

    public String getLastWinnerName() { 
        if (lastWinner == null) {
            return "no one (tie)";
        }
        return lastWinner.getName();
    }

    public String getPlayerName() { return player.getName(); }
    public String getEnemyName() { return enemy.getName(); }
    public List<Card> getPlayerCards() { return player.getCards(); }
    public List<Card> getEnemyCards() { return enemy.getCards(); }
    public int getPlayerWins() { return playerWins; }
    public int getEnemyWins() { return enemyWins; }
}
