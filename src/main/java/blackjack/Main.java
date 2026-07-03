package blackjack;

import java.util.List;

import blackjack.controller.BlackjackController;
import blackjack.core.BlackjackCore;
import blackjack.entity.AIRecord;
import blackjack.entity.CombatEntity;
import blackjack.entity.EnemyFactory;
import blackjack.entity.Entity;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.BlackjackViewTerminal;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
        Entity player = new CombatEntity("Player", 50);
        BlackjackCore core = new BlackjackCore(player);
        BlackjackController controller = new BlackjackController(core);
        BlackjackViewTerminal view = new BlackjackViewTerminal(io, controller);

        EnemyFactory enemyFactory = new EnemyFactory();

        while (true) {
            List<AIRecord> enemyRecords = enemyFactory.generateThreeRandomEnemy((float) 1);

            int count = 1;
            for (AIRecord enemyRecord : enemyRecords) {
                io.printMessage("Enemy " + count);
                count++;
                controller.startCombat(enemyRecord);
                if (!player.isAlive()) {
                    io.printMessage("Game Over! You're trash lol");
                    return;
                }
                player.battleReset();
                // add shop here
            }

            io.printMessage("Three done");
        }
    }
}