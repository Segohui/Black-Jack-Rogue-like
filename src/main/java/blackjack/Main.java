package blackjack;

import blackjack.controller.ControllerFactory;
import blackjack.core.battle.BattleCore;
import blackjack.core.cards.Deck;
import blackjack.core.inventory.Inventory;
import blackjack.core.inventory.ItemRegistry;
import blackjack.core.shop.Shop;
import blackjack.entity.CombatEntity;
import blackjack.entity.Entity;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.view.ViewRouter;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
        Deck playerDeck = new Deck();
        Inventory playerInventory = new Inventory();
        ItemRegistry itemRegistry = new ItemRegistry();
        Entity player = new CombatEntity("Player", playerDeck, 50, true);
        Shop shop = new Shop(playerInventory, playerDeck, itemRegistry);
        BattleCore core = new BattleCore(player);
        ControllerFactory controllerFactory = new ControllerFactory(core, shop, playerInventory);
        GameManager gameManager = new GameManager(controllerFactory);
        ViewRouter router = new ViewRouter(io, gameManager); // doesnt need to be used (only recieve signals)
        
        gameManager.startMainMenu();
    }
}