package blackjack.visual.terminal.view;

import blackjack.controller.MenuController;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.menu.MenuScreenFactory;

public class MenuViewTerminal {
    private final InputOutput io;
    private final MenuController controller;
    private final MenuScreenFactory menuScreenFactory;

    public MenuViewTerminal(InputOutput io, MenuController controller) {
        this.io = io;
        this.controller = controller;
        this.menuScreenFactory = new MenuScreenFactory(io, controller);

        // for now, there is no need to expand this one
        menuScreenFactory.createTitleScreen().render();
    }
}
