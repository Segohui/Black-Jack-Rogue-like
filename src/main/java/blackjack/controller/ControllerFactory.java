package blackjack.controller;

import blackjack.core.battle.BattleCore;
import blackjack.core.inventory.Inventory;
import blackjack.core.shop.Shop;

public class ControllerFactory {
    private final BattleCore core;
    private final Shop shop;
    private final Inventory playerInventory;

    public ControllerFactory(BattleCore core, Shop shop, Inventory playerInventory) {
        this.core = core;
        this.shop = shop;
        this.playerInventory = playerInventory;
    }

    public BattleController createBattle() {
        return new BattleController(core);
    }

    public ShopController createShop() {
        return new ShopController(shop, playerInventory);
    }

    public MenuController createMenu() {
        return new MenuController();
    }
}