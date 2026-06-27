package blackjack.core;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.entity.CombatEntity;

public class CombatRunner {
    // maybe initialize observers outside of combatRunner
    private final Signal enemyPlayed = new Signal();
    private final Signal gameOver = new Signal();

    private final CombatEntity player;
    private CombatEntity enemy;
    private int playerWins = 0;
    private int enemyWins = 0;
    private CombatEntity lastWinner = null;

    public CombatRunner(CombatEntity player) {
        this.player = player;
    }

    public void startRound(CombatEntity enemy) {
        this.enemy = enemy;
        
        enemy.drawCardToHand();
        enemy.drawCardToHand();
        player.drawCardToHand();
        player.drawCardToHand();
    }

    public void processMove() {
        int playerSum = calculatePlayerSum();
        int enemySum = calculateEnemySum();
        if (playerSum == 21 && enemySum == 21) {
            lastWinner = null;
            gameOver.emit();
        } else if (playerSum == 21 || enemySum > 21) {
            playerWins++;
            lastWinner = player;
            gameOver.emit();
        } else if (enemySum == 21 || playerSum > 21) {
            enemyWins++;
            lastWinner = enemy;
            gameOver.emit();
        } else if (enemySum < 17) {
            enemy.drawCardToHand();
            enemySum = calculateEnemySum();
            if (enemySum > 21) {
                playerWins++;
                lastWinner = player;
                gameOver.emit();
                return;
            }
            enemyPlayed.emit();
        } else {
            enemyPlayed.emit();
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

    public int calculateEnemySum() {
        return enemy.calculateSum();
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getEnemyWins() {
        return enemyWins;
    }

    public void gameOverConnect(Runnable runnable) {
        gameOver.connect(runnable);
    }

    public void enemyPlayedConnect(Runnable runnable) {
        enemyPlayed.connect(runnable);
    }

    public List<Card> getPlayerCards() {
        return player.getCards();
    }

    public List<Card> getEnemyCards() {
        return enemy.getCards();
    }

    public String getLastWinner() {
        if (lastWinner.equals(player)) {
            return "player";
        } else if (lastWinner.equals(enemy)) {
            return "enemy";
        }
        return "tie";
    }
}
