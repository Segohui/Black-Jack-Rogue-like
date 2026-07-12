package blackjack;

import blackjack.controller.ControllerFactory;
import blackjack.core.battle.BattleCore;
import blackjack.core.shop.Shop;
import blackjack.entity.CombatEntity;
import blackjack.entity.Entity;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.view.ViewRouter;

public class GameInitializer {
    private final InputOutput io;

    public GameInitializer() {
        this.io = new InputOutput();
    }

    public void startNewGame() {
        Entity player = new CombatEntity("Player", 1, true);
        Shop shop = new Shop(null, player);
        BattleCore core = new BattleCore(player);
        
        ControllerFactory controllerFactory = new ControllerFactory(core, shop);
        GameManager gameManager = new GameManager(controllerFactory);
        gameManager.restartGameConnect(this::startNewGame);
        ViewRouter router = new ViewRouter(io, gameManager);
        
        gameManager.startMainMenu();
    }
}