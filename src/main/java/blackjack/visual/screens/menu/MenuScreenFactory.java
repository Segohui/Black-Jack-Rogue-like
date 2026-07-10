package blackjack.visual.screens.menu;

import blackjack.controller.MenuController;
import blackjack.visual.InputOutput;
import blackjack.visual.screens.Screen;

public class MenuScreenFactory {
    private final InputOutput io;
    private final MenuController controller;

    public MenuScreenFactory(InputOutput io, MenuController controller) {
        this.io = io;
        this.controller = controller;
    }

    public Screen createTitleScreen() {
        return new TitleScreen(io, controller);
    }
}
