package blackjack;

import blackjack.controller.ControllerFactory;
import blackjack.core.BattleCore;
import blackjack.core.shop.Shop;
import blackjack.entity.CombatEntity;
import blackjack.entity.Entity;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.view.ViewRouter;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
        Entity player = new CombatEntity("Player", 50);
        Shop shop = new Shop(null, player);
        BattleCore core = new BattleCore(player);
        ControllerFactory controllerFactory = new ControllerFactory(core, shop);
        GameManager gameManager = new GameManager(controllerFactory);
        ViewRouter router = new ViewRouter(io, gameManager); // doesnt need to be used (only recieve signals)
        
        gameManager.startMainMenu();
    }
}