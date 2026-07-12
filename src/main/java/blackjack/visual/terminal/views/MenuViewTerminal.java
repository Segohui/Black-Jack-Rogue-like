package blackjack.visual.terminal.views;

import blackjack.controller.MenuController;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;
import blackjack.visual.terminal.screens.menu.MenuScreenFactory;

public class MenuViewTerminal {
    private final InputOutput io;
    private final MenuScreenFactory menuScreenFactory;

    public MenuViewTerminal(InputOutput io, MenuController controller) {
        this.io = io;
        this.menuScreenFactory = new MenuScreenFactory(io, controller);

        controller.startMenuConnect(this::renderStartMenu);
        controller.instructionsSelectedConnect(this::onInstructionsSelected);
        controller.quitSelectedConnect(this::onQuitSelected);
        controller.playerLoseConnect(this::onPlayerLose);
    }

    private void renderStartMenu() {
        io.clearScreen();
        menuScreenFactory.createTitleScreen().render();
    }

    private void navigateToScreen(Screen newScreen) {
        io.clearScreen();
        newScreen.render();
    }

    public void onInstructionsSelected() {
        navigateToScreen(menuScreenFactory.createInstructionsScreen());
        renderStartMenu();
    }

    public void onQuitSelected() {
        navigateToScreen(menuScreenFactory.createQuitScreen());
    }

    public void onPlayerLose() {
        navigateToScreen(menuScreenFactory.createLoseScreen());
    }
}
