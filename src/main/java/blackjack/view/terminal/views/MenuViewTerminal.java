package blackjack.view.terminal.views;

import blackjack.controller.MenuController;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;
import blackjack.view.terminal.screens.menu.MenuScreenFactory;

/**
 * Terminal view coordinator for the main menu.
 *
 * <p>Wires menu controller events to the appropriate menu screens.</p>
 */
public class MenuViewTerminal {
    private final InputOutput io;
    private final MenuScreenFactory menuScreenFactory;

    /**
     * Creates a menu view router for the given controller.
     *
     * @param io console I/O helper
     * @param controller menu controller whose signals will be rendered
     */
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

    /**
     * Clears the terminal and renders a new screen.
     *
     * @param newScreen screen to render
     */
    private void navigateToScreen(Screen newScreen) {
        io.clearScreen();
        newScreen.render();
    }

    /**
     * Handles the instructions menu selection.
     */
    public void onInstructionsSelected() {
        navigateToScreen(menuScreenFactory.createInstructionsScreen());
        renderStartMenu();
    }

    /**
     * Handles the quit menu selection.
     */
    public void onQuitSelected() {
        navigateToScreen(menuScreenFactory.createQuitScreen());
    }

    /**
     * Handles the player defeat event by displaying the lose screen.
     */
    public void onPlayerLose() {
        navigateToScreen(menuScreenFactory.createLoseScreen());
    }
}
