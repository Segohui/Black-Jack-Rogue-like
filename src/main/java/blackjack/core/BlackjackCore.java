package blackjack.core;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.entity.CombatEntity;
import blackjack.entity.Enemy;
import blackjack.entity.Player;

public class BlackjackCore {
    private final Signal nextTurn = new Signal();
    private final Signal gameOver = new Signal();

    private final Player player;
    private Enemy enemy;
    private CombatEntity lastWinner;

    private int playerWins = 0;
    private int playerLoses = 0;
    private int globalStand = 21; // may change with power ups
    private boolean playerStand = false;

    public BlackjackCore(Player player) {
        this.player = player;
    }

    public void startRound(Enemy enemy) {
        this.enemy = enemy;
        this.playerStand = false;
        this.lastWinner = null;
        
        enemy.drawCardToHand();
        enemy.drawCardToHand();
        player.drawCardToHand();
        player.drawCardToHand();
    }

    public void playerHit() {
        player.drawCardToHand();
        processMove();
    }

    public void playerStand() {
        this.playerStand = true;
        processMove();
    }

    private void processMove() {
        int playerSum = player.calculateSum();

        if (playerSum > globalStand) {
            endGame(enemy);
            return;
        }

        if (playerStand) {
            int enemySum = enemy.calculateSum();
            int enemyLimit = globalStand - enemy.getStandThreshold();
            
            while (enemySum < enemyLimit) {
                enemy.drawCardToHand();
                enemySum = enemy.calculateSum();
            }

            if (enemySum > globalStand) {
                endGame(player);
            } else {
                evaluateWinner(playerSum, enemySum);
            }
        } else {
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
        this.lastWinner = winner;
        if (winner != null) {
            if (winner.isPlayerControlled()) playerWins++;
            else playerLoses++;
        }
        gameOver.emit();
    }

    public int calculatePlayerSum() {
        return player.calculateSum();
    }

    public int calculateEnemySum() {
        return enemy.calculateSum();
    }

    public void gameOverConnect(Runnable runnable) { gameOver.connect(runnable); }
    public void nextTurnConnect(Runnable runnable) { nextTurn.connect(runnable); }
    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }
    public String getPlayerName() { return player.getName(); }
    public String getEnemyName() { return enemy.getName(); }
    public List<Card> getPlayerCards() { return player.getCards(); }
    public List<Card> getEnemyCards() { return enemy.getCards(); }
    public int getPlayerWins() { return playerWins; }
    public int getPlayerLoses() { return playerLoses; }
    public CombatEntity getLastWinner() { return lastWinner; }
    public boolean getPlayerStand() { return playerStand; }
    public int getGlobalStand() { return globalStand; }
}
