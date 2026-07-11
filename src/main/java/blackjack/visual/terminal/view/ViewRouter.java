package blackjack.visual.terminal.view;

import blackjack.GameManager;
import blackjack.controller.BattleController;
import blackjack.controller.MenuController;
import blackjack.controller.ShopController;
import blackjack.visual.InputOutput;

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