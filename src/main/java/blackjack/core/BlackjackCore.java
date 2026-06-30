package blackjack.core;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.dto.DamageEventData;
import blackjack.entity.CombatEntity;
import blackjack.entity.Enemy;
import blackjack.entity.Player;

public class BlackjackCore {
    private final Signal nextTurn = new Signal();
    private final Signal roundOver = new Signal();
    private final Signal gameOver = new Signal();
    private final Signal takeDamage = new Signal();

    private final Player player;
    private Enemy enemy;
    private CombatEntity winner;
    private DamageEventData lastDamageEvent;

    private int globalStand = 21; // may change with power ups
    private boolean playerStand = false;

    public BlackjackCore(Player player) {
        this.player = player;
    }

    public void startRound(Enemy enemy) {
        this.enemy = enemy;
        this.playerStand = false;
        this.winner = null;

        enemy.resetHand();
        player.resetHand();
        
        enemy.drawCardToHand();
        enemy.drawCardToHand();
        player.drawCardToHand();
        player.drawCardToHand();

        nextTurn.emit();
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
        int enemySum = enemy.calculateSum();

        if (playerSum > globalStand) {
            endRound(enemy, player, enemySum);
            return;
        }

        int enemyLimit = globalStand - enemy.getStandThreshold();

        if (playerStand) {
            while (enemySum < enemyLimit) {
                enemy.drawCardToHand();
                enemySum = enemy.calculateSum();
            }

            if (enemySum > globalStand) {
                endRound(player, enemy, playerSum);
            } else {
                evaluateWinner(playerSum, enemySum);
            }
        } else {
            if (enemySum < enemyLimit) {
                enemy.drawCardToHand();
                enemySum = calculateEnemySum();

                if (enemySum > globalStand) {
                    endRound(player, enemy, playerSum);
                    return;
                }
            }
            
            nextTurn.emit();
        }
    }

    private void evaluateWinner(int playerSum, int enemySum) {
        if (playerSum > enemySum) {
            endRound(player, enemy, playerSum);
        } else if (enemySum > playerSum) {
            endRound(enemy, player, enemySum);
        } else {
            endRound(null, null, 0);
        }
    }

    private void endRound(CombatEntity winner, CombatEntity loser, int damage) {
        this.winner = winner;
        if (winner != null && loser != null) {
            loser.takeDamage(damage);
            this.lastDamageEvent = new DamageEventData(loser.getName(), damage);
            takeDamage.emit();
        }

        if (!player.isAlive() || !enemy.isAlive()) {
            gameOver.emit();
            return;
        }

        roundOver.emit();
        startRound(enemy);
    }

    public int calculatePlayerSum() {
        return player.calculateSum();
    }

    public int calculateEnemySum() {
        return enemy.calculateSum();
    }

    public void roundOverConnect(Runnable runnable) { roundOver.connect(runnable); }
    public void nextTurnConnect(Runnable runnable) { nextTurn.connect(runnable); }
    public void gameOverConnect(Runnable runnable) { gameOver.connect(runnable); }
    public void takeDamageConnect(Runnable runnable) { takeDamage.connect(runnable); }
    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }
    public String getPlayerName() { return player.getName(); }
    public String getEnemyName() { return enemy.getName(); }
    public int getPlayerCurrentHp() { return player.getCurrentHp(); }
    public int getEnemyCurrentHp() { return enemy.getCurrentHp(); }
    public List<Card> getPlayerCards() { return player.getCards(); }
    public List<Card> getEnemyCards() { return enemy.getCards(); }
    public CombatEntity getWinner() { return winner; }
    public DamageEventData getLastDamageEvent() {return lastDamageEvent; }
    public boolean getPlayerStand() { return playerStand; }
    public int getGlobalStand() { return globalStand; }
}
