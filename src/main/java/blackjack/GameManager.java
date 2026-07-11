package blackjack;

import java.util.function.Consumer;
import blackjack.controller.BattleController;
import blackjack.controller.ControllerFactory;
import blackjack.controller.MenuController;
import blackjack.controller.ShopController;
import blackjack.core.DataSignal;
import blackjack.entity.EnemyFactory;

public class GameManager {
    private final DataSignal<BattleController> battleStarted = new DataSignal<>();
    private final DataSignal<ShopController> shopStarted = new DataSignal<>();
    private final DataSignal<MenuController> menuStarted = new DataSignal<>();

    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final ControllerFactory controllerFactory;

    public GameManager(ControllerFactory controllerFactory) {
        this.controllerFactory = controllerFactory;
    }

    // Game States

    public void startMainMenu() {
        MenuController menuController = controllerFactory.createMenu();
        
        menuController.playSelectedConnect(this::startCombatRoom);
        menuController.quitSelectedConnect(this::exitGame);
        
        menuStarted.emit(menuController);
    }

    public void startCombatRoom() {
        BattleController battleController = controllerFactory.createBattle();
        battleController.playerAliveConnect(this::onBattleEnd);
        battleController.initializeEnemy(enemyFactory.generateRandomEnemy((float) 1));
        battleStarted.emit(battleController);
        battleController.startBattle();
    }

    public void startShopRoom() {
        ShopController shopController = controllerFactory.createShop();
        
        shopController.shopExitedConnect(v -> startCombatRoom());
        
        shopStarted.emit(shopController);
        shopController.openShop();
    }

    private void onBattleEnd(boolean isPlayerAlive) {
        if (isPlayerAlive) {
            startShopRoom();
        } else {
            exitGame();
        }
    }

    public void exitGame() {
        System.exit(0);
    }

    // Connects

    public void battleStartedConnect(Consumer<BattleController> listener) {
        battleStarted.connect(listener);
    }

    public void shopStartedConnect(Consumer<ShopController> listener) { 
        shopStarted.connect(listener);
    }

    public void menuStartedConnect(Consumer<MenuController> listener) {
        menuStarted.connect(listener);
    }
}