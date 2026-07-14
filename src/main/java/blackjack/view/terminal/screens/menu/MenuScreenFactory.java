package blackjack.view.terminal.screens.menu;

import blackjack.controller.MenuController;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

/**
 * Factory that creates menu screens for the terminal UI.
 */
public class MenuScreenFactory {
    private final InputOutput io;
    private final MenuController controller;

    /**
     * Creates a factory for menu screens.
     *
     * @param io terminal I/O helper
     * @param controller menu controller used by the screens
     */
    public MenuScreenFactory(InputOutput io, MenuController controller) {
        this.io = io;
        this.controller = controller;
    }

    public Screen createTitleScreen() {
        return new TitleScreen(io, controller);
    }

    public Screen createInstructionsScreen() {
        return new InstructionsScreen(io);
    }

    public Screen createQuitScreen() {
        return new QuitScreen(io);
    }

    public Screen createLoseScreen() {
        return new LoseScreen(io, controller);
    }
}
