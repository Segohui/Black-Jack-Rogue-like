package blackjack;

import java.util.List;

import blackjack.controller.BlackjackController;
import blackjack.core.BlackjackCore;
import blackjack.core.shop.Shop;
import blackjack.core.shop.ShopItemFactory;
import blackjack.entity.AIRecord;
import blackjack.entity.CombatEntity;
import blackjack.entity.EnemyFactory;
import blackjack.entity.Entity;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.BlackjackViewTerminal;
import blackjack.visual.terminal.screens.ScreenFactory;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
        Entity player = new CombatEntity("Player", 50);
        BlackjackCore core = new BlackjackCore(player);
        BlackjackController controller = new BlackjackController(core);
        new BlackjackViewTerminal(io, controller);

        EnemyFactory enemyFactory = new EnemyFactory();
        ShopItemFactory shopItemFactory = new ShopItemFactory();
        ScreenFactory screenFactory = new ScreenFactory(io, controller);

        while (true) {
            List<AIRecord> enemyRecords = enemyFactory.generateThreeRandomEnemy((float) 0.1);

            for (AIRecord enemyRecord : enemyRecords) {
                controller.startCombat(enemyRecord);
                if (!player.isAlive()) {
                    io.printMessage("Game Over! You're trash lol");
                    return;
                }
                player.battleReset();
                
                Shop shop = new Shop(shopItemFactory.generateShopItems(3));
                screenFactory.createShopScreen(shop).render();
            }
        }
    }
}