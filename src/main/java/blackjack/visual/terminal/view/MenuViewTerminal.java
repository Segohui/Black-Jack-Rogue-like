package blackjack.visual.terminal.view;

import blackjack.controller.MenuController;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;
import blackjack.visual.terminal.screens.menu.MenuScreenFactory;

public class MenuViewTerminal {
    private final InputOutput io;
    private final MenuController controller;
    private final MenuScreenFactory menuScreenFactory;

    public MenuViewTerminal(InputOutput io, MenuController controller) {
        this.io = io;
        this.controller = controller;
        this.menuScreenFactory = new MenuScreenFactory(io, controller);

        controller.startMenuConnect(this::renderStartMenu);
        controller.instructionsSelectedConnect(this::onInstructionsSelected);
    }

    private void renderStartMenu() {
        io.clearScreen();
        menuScreenFactory.createTitleScreen().render();
    }

    private void navigateToScreen(Screen newScreen) {
        io.clearScreen();
        newScreen.render();
        renderStartMenu();
    }

    public void onInstructionsSelected() {
        navigateToScreen(menuScreenFactory.createInstructionsScreen());
    }
}
