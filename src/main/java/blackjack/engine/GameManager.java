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

/**
 * Orchestrates high-level game flow and room transitions.
 *
 * <p>The game manager sequences the main menu, combat rooms, shop rooms, and
 * restart behavior while emitting signals for view routing.</p>
 */
public class GameManager {
    private final DataSignal<BattleController> battleStarted = new DataSignal<>();
    private final DataSignal<ShopController> shopStarted = new DataSignal<>();
    private final DataSignal<MenuController> menuStarted = new DataSignal<>();
    private final EmptySignal restartGame = new EmptySignal();
    private final ControllerFactory controllerFactory;

    private AbstractEnemyFactory currentEnemyFactory;
    private float difficultyMultiplier = 1;
    private int combatsWon = 0;

    /**
     * Creates a new game manager with the provided controller factory.
     *
     * @param controllerFactory factory used to build controllers for each room
     */
    public GameManager(ControllerFactory controllerFactory) {
        this.controllerFactory = controllerFactory;
        this.currentEnemyFactory = new CombinedEnemiesFactory();
    }

    // Game States

    /**
     * Begins the main menu state and notifies listeners that the menu is ready.
     */
    public void startMainMenu() {
        MenuController menuController = controllerFactory.createMenu();
        
        menuController.playSelectedConnect(this::startCombatRoom);
        menuController.quitSelectedConnect(this::exitGame);
        
        menuStarted.emit(menuController);
        menuController.startMenu();
    }

    /**
     * Starts a new combat room and initializes battle progression.
     */
    public void startCombatRoom() {
        BattleController battleController = controllerFactory.createBattle();
        
        battleController.prepareBattleStage(currentEnemyFactory.generateRandomEnemy(difficultyMultiplier));
        
        battleController.gameOverConnect(this::onBattleEnd);
        
        battleStarted.emit(battleController);
        
        battleController.startBattle();
    }

    /**
     * Enters the shop room once a combat has finished successfully.
     */
    /**
     * Enters the shop room and emits the corresponding start signal.
     */
    private void startShopRoom() {
        ShopController shopController = controllerFactory.createShop();
        
        shopController.shopExitedConnect(this::startCombatRoom);
        
        shopStarted.emit(shopController);
        shopController.enterShop();
    }

    /**
     * Switches the game to the lose menu after the player dies.
     */
    private void playerLoseMenu() {
        MenuController menuController = controllerFactory.createMenu();
        menuStarted.emit(menuController);
        menuController.restartGameConnect(this::restartGame);
        menuController.selectLose();
    }

    /**
     * Emits the restart game signal when a new run is requested.
     */
    private void restartGame() {
        restartGame.emit();
    }

    /**
     * Handles the end-of-battle transition.
     *
     * @param playerAlive true when the player survives combat
     */
    private void onBattleEnd(boolean playerAlive) {
        if (playerAlive) {
            // The enemies get stronger after 3 cleared
            if (combatsWon % 3 == 0) {
                difficultyMultiplier += 0.2;
            }
            startShopRoom();
        } else {
            playerLoseMenu();
        }
    }

    /**
     * Changes the enemy factory used to generate future opponents.
     *
     * @param newEnemyFactory new factory to generate enemy blueprints
     */
    public void changeEnemyFactory(AbstractEnemyFactory newEnemyFactory) {
        this.currentEnemyFactory = newEnemyFactory;
    }

    /**
     * Exits the application.
     */
    public void exitGame() {
        System.exit(0);
    }

    // Connects

    /**
     * Registers a listener for battle start events.
     *
     * @param listener callback invoked when a battle controller is ready
     */
    public void battleStartedConnect(Consumer<BattleController> listener) {
        battleStarted.connect(listener);
    }

    /**
     * Registers a listener for shop start events.
     *
     * @param listener callback invoked when the shop controller is ready
     */
    public void shopStartedConnect(Consumer<ShopController> listener) { 
        shopStarted.connect(listener);
    }

    /**
     * Registers a listener for menu start events.
     *
     * @param listener callback invoked when the menu controller is ready
     */
    public void menuStartedConnect(Consumer<MenuController> listener) {
        menuStarted.connect(listener);
    }

    /**
     * Registers a listener for restart game events.
     *
     * @param runnable callback invoked when a game restart is requested
     */
    public void restartGameConnect(Runnable runnable) {
        restartGame.connect(runnable);
    }
}