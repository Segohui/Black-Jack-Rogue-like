package blackjack.controller;

import blackjack.core.battle.BattleCore;
import blackjack.core.inventory.Inventory;
import blackjack.core.shop.Shop;

/**
 * Creates the controller objects used by the game flow.
 */
public class ControllerFactory {
    private final BattleCore core;
    private final Shop shop;
    private final Inventory playerInventory;

    /**
     * Creates a controller factory bound to the shared game services.
     *
     * @param core battle engine used by battle controllers
     * @param shop shop domain used by shop controllers
     * @param playerInventory inventory shared across controllers
     */
    public ControllerFactory(BattleCore core, Shop shop, Inventory playerInventory) {
        this.core = core;
        this.shop = shop;
        this.playerInventory = playerInventory;
    }

    /**
     * Creates a battle controller for the current game session.
     *
     * @return new battle controller
     */
    public BattleController createBattle() {
        return new BattleController(core, playerInventory);
    }

    /**
     * Creates a shop controller for the current game session.
     *
     * @return new shop controller
     */
    public ShopController createShop() {
        return new ShopController(shop, playerInventory);
    }

    /**
     * Creates a menu controller for the current game session.
     *
     * @return new menu controller
     */
    public MenuController createMenu() {
        return new MenuController();
    }
}