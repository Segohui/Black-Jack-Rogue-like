package blackjack;

import java.util.List;

import blackjack.core.BlackjackController;
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
        BlackjackViewTerminal view = new BlackjackViewTerminal(io);
        BlackjackCore core = new BlackjackCore(player);
        BlackjackController controller = new BlackjackController(view, core);

        EnemyFactory enemyFactory = new EnemyFactory();

        while (true) {
            List<Enemy> enemies = enemyFactory.generateThreeRandomEnemy(1);

            int count = 1;
            for (Enemy enemy : enemies) {
                io.printMessage("Enemy " + count);
                count++;
                controller.startCombat(enemy);
                player.resetPlayerCards();
                // add shop here
            }

            io.printMessage("Three done");
        }
    }
}