package blackjack;

import java.util.List;

import blackjack.core.CombatRunner;
import blackjack.entity.Enemy;
import blackjack.entity.EnemyFactory;
import blackjack.entity.Player;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.TerminalBlackjack;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
        Player player = new Player();
        CombatRunner combatRunner = new CombatRunner(player);
        TerminalBlackjack terminal = new TerminalBlackjack(player, combatRunner, io);

        EnemyFactory enemyFactory = new EnemyFactory();

        while (true) {
            List<Enemy> enemies = enemyFactory.generateThreeRandomEnemy(1);

            for (Enemy enemy : enemies) {
                terminal.startCombat(enemy);
                player.resetPlayer();
            }

            io.printMessage("Three done");
        }
    }
}