package blackjack.controller;

import blackjack.core.battle.BattleCore;
import blackjack.core.shop.Shop;
import blackjack.entity.AIRecord;

public class ControllerFactory {
    private final BattleCore core;
    private final Shop shop;

    public ControllerFactory(BattleCore core, Shop shop) {
        this.core = core;
        this.shop = shop;
    }

    public BattleController createBattle(AIRecord aiRecord) {
        return new BattleController(core, aiRecord);
    }

    public ShopController createShop() {
        return new ShopController(shop);
    }

    public MenuController createMenu() {
        return new MenuController();
    }
}