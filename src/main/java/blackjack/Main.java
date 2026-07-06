package blackjack;

import java.util.List;

import blackjack.controller.BlackjackController;
import blackjack.core.BlackjackCore;
import blackjack.core.shop.Shop;
import blackjack.core.shop.ShopItemFactory;
import blackjack.entity.Enemy;
import blackjack.entity.EnemyFactory;
import blackjack.entity.Player;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.BlackjackViewTerminal;
import blackjack.visual.terminal.screens.ScreenFactory;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
        Player player = new Player("Player");
        BlackjackCore core = new BlackjackCore(player);
        BlackjackController controller = new BlackjackController(core);
        new BlackjackViewTerminal(io, controller);

        EnemyFactory enemyFactory = new EnemyFactory();
        ShopItemFactory shopItemFactory = new ShopItemFactory();
        ScreenFactory screenFactory = new ScreenFactory(io, controller);

        while (true) {
            List<Enemy> enemies = enemyFactory.generateThreeRandomEnemy((float) 0.1);

            for (Enemy enemy : enemies) {
                controller.startCombat(enemy);
                if (!player.getHealthComponent().isAlive()) {
                    return;
                }
                player.resetPlayer();
                
                Shop shop = new Shop(shopItemFactory.generateShopItems(3));
                screenFactory.createShopScreen(shop).render();
            }
        }
    }
}