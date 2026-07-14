package blackjack.engine;

import java.util.function.Consumer;
import blackjack.controller.BattleController;
import blackjack.controller.ControllerFactory;
import blackjack.controller.MenuController;
import blackjack.controller.ShopController;
import blackjack.core.entity.enemy.factory.AbstractEnemyFactory;
import blackjack.core.entity.enemy.factory.CombinedEnemiesFactory;
import blackjack.core.signal.DataSignal;
import blackjack.core.signal.EmptySignal;
import blackjack.dtos.core.battle.CombatOverDTO;

public class GameManager {
    private final DataSignal<BattleController> battleStarted = new DataSignal<>();
    private final DataSignal<ShopController> shopStarted = new DataSignal<>();
    private final DataSignal<MenuController> menuStarted = new DataSignal<>();
    private final EmptySignal restartGame = new EmptySignal();
    private final ControllerFactory controllerFactory;

    private AbstractEnemyFactory currentEnemyFactory;
    private float difficultyMultiplier = 1;
    private int combatsWon = 0;

    public GameManager(ControllerFactory controllerFactory) {
        this.controllerFactory = controllerFactory;
        this.currentEnemyFactory = new CombinedEnemiesFactory();
    }

    // Game States

    public void startMainMenu() {
        MenuController menuController = controllerFactory.createMenu();
        
        menuController.playSelectedConnect(this::startCombatRoom);
        menuController.quitSelectedConnect(this::exitGame);
        
        menuStarted.emit(menuController);
        menuController.startMenu();
    }

    public void startCombatRoom() {
        BattleController battleController = controllerFactory.createBattle();
        
        battleController.prepareBattleStage(currentEnemyFactory.generateRandomEnemy(difficultyMultiplier));
        
        battleController.combatOverDataConnect(this::onBattleEnd);
        
        battleStarted.emit(battleController);
        
        battleController.startBattle();
    }

    private void startShopRoom() {
        ShopController shopController = controllerFactory.createShop();
        
        shopController.shopExitedConnect(this::startCombatRoom);
        
        shopStarted.emit(shopController);
        shopController.enterShop();
    }

    private void playerLoseMenu() {
        MenuController menuController = controllerFactory.createMenu();
        menuStarted.emit(menuController);
        menuController.restartGameConnect(this::restartGame);
        menuController.selectLose();
    }

    private void restartGame() {
        restartGame.emit();
    }

    private void onBattleEnd(CombatOverDTO combatOverDTO) {
        if (combatOverDTO.isPlayerControlled()) {
            // The enemies get stronger after 3 cleared
            if (combatsWon % 3 == 0) {
                difficultyMultiplier += 0.2;
            }
            startShopRoom();
        } else {
            playerLoseMenu();
        }
    }

    public void changeEnemyFactory(AbstractEnemyFactory newEnemyFactory) {
        this.currentEnemyFactory = newEnemyFactory;
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

    public void restartGameConnect(Runnable runnable) {
        restartGame.connect(runnable);
    }
}