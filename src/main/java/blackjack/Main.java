package blackjack;

import java.util.List;

import blackjack.controller.BlackjackController;
import blackjack.core.BlackjackCore;
import blackjack.entity.Enemy;
import blackjack.entity.EnemyFactory;
import blackjack.entity.Player;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.BlackjackViewTerminal;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
        Player player = new Player("Player");
        BlackjackCore core = new BlackjackCore(player);
        BlackjackController controller = new BlackjackController(core);
        BlackjackViewTerminal view = new BlackjackViewTerminal(io, controller);

        EnemyFactory enemyFactory = new EnemyFactory();

        while (true) {
            List<Enemy> enemies = enemyFactory.generateThreeRandomEnemy((float) 0.2);

            int count = 1;
            for (Enemy enemy : enemies) {
                io.printMessage("Enemy " + count);
                count++;
                controller.startCombat(enemy);
                if (!player.getHealthComponent().isAlive()) {
                    io.printMessage("Game Over! You're trash lol");
                    return;
                }
                player.resetPlayer();
                // add shop here
            }

            io.printMessage("Three done");
        }
    }
}