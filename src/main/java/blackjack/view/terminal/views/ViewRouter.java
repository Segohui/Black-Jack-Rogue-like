package blackjack.view.terminal.views;

import blackjack.engine.GameManager;
import blackjack.view.terminal.io.InputOutput;
import blackjack.controller.BattleController;
import blackjack.controller.MenuController;
import blackjack.controller.ShopController;

public class ViewRouter {
    private final InputOutput io;
    private Object currentActiveView;

    public ViewRouter(InputOutput io, GameManager manager) {
        this.io = io;
        manager.menuStartedConnect(this::onMenuStarted);
        manager.battleStartedConnect(this::onBattleStarted);
        manager.shopStartedConnect(this::onStoreStarted);
    }

    private void onBattleStarted(BattleController battleController) {
        io.clearScreen();
        this.currentActiveView = new BattleViewTerminal(io, battleController);
    }

    private void onStoreStarted(ShopController shopController) {
        io.clearScreen();
        this.currentActiveView = new ShopViewTerminal(io, shopController);
    }

    private void onMenuStarted(MenuController menuController) {
        io.clearScreen();
        this.currentActiveView = new MenuViewTerminal(io, menuController);
    }
}