package blackjack.engine;

import blackjack.controller.ControllerFactory;
import blackjack.core.battle.BattleCore;
import blackjack.core.cards.Deck;
import blackjack.core.entity.CombatEntity;
import blackjack.core.entity.capabilities.Entity;
import blackjack.core.inventory.Inventory;
import blackjack.core.inventory.ItemRegistry;
import blackjack.core.shop.Shop;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.views.ViewRouter;

/**
 * Initializes the game domain and prepares the first session.
 *
 * <p>This class assembles the player, inventory, shop, battle core, controller
 * factory, game manager, and terminal view router for the application.</p>
 */
public class GameInitializer {
    private final InputOutput io; 

    /**
     * Creates a new game initializer with the default terminal I/O provider.
     */
    public GameInitializer() {
        this.io = new InputOutput();
    }

    /**
     * Constructs the game objects and begins execution at the main menu.
     *
     * <p>This method wires the domain-level objects and connects the game
     * manager to the terminal view router.</p>
     */
    public void startNewGame() {
        Deck playerDeck = new Deck();
        Entity player = new CombatEntity("Player", playerDeck, 50, true);
        
        Inventory playerInventory = new Inventory();
        ItemRegistry itemRegistry = new ItemRegistry();
        
        Shop shop = new Shop(playerInventory, playerDeck, itemRegistry);
        BattleCore core = new BattleCore(player, playerDeck, playerInventory);
        
        ControllerFactory controllerFactory = new ControllerFactory(core, shop, playerInventory);
        GameManager gameManager = new GameManager(controllerFactory);
        ViewRouter router = new ViewRouter(io, gameManager);
        
        gameManager.restartGameConnect(this::startNewGame);

        gameManager.startMainMenu();
    }
}