package blackjack;

import java.util.List;

import blackjack.entity.Enemy;
import blackjack.entity.EnemyFactory;
import blackjack.entity.Player;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.TerminalBlackjack;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
        Player player = new Player("Player");
        TerminalBlackjack terminal = new TerminalBlackjack(io, player);

        EnemyFactory enemyFactory = new EnemyFactory();

        while (true) {
            List<Enemy> enemies = enemyFactory.generateThreeRandomEnemy(1);

            int count = 1;
            for (Enemy enemy : enemies) {
                io.printMessage("Enemy " + count);
                count++;
                terminal.runCombat(player, enemy);
                player.resetPlayerCards();
                // add store here
            }

            io.printMessage("Three done");
        }
    }
}