package blackjack.controller;

import blackjack.core.signal.EmptySignal;

/**
 * Controller for main menu state and menu-related user actions.
 *
 * <p>This class uses signals to decouple menu actions from the view
 * implementation.</p>
 */
public class MenuController {
    private final EmptySignal playSelected = new EmptySignal();
    private final EmptySignal quitSelected = new EmptySignal();
    private final EmptySignal playerLose = new EmptySignal();
    private final EmptySignal instructionsSelected = new EmptySignal();
    private final EmptySignal startMenu = new EmptySignal();
    private final EmptySignal restartGame = new EmptySignal();

    /**
     * Emits the signal to render the main menu.
     */
    public void startMenu() {
        startMenu.emit();
    }

    /**
     * Emits the signal to display the instructions screen.
     */
    public void selectInstructions() {
        instructionsSelected.emit();
    }

    /**
     * Emits the signal to begin gameplay.
     */
    public void selectPlay() {
        playSelected.emit();
    }

    /**
     * Emits the signal to quit the application.
     */
    public void selectQuit() {
        quitSelected.emit();
    }

    /**
     * Emits the signal indicating the player has lost and should see the lose screen.
     */
    public void selectLose() {
        playerLose.emit();
    }

    /**
     * Emits the signal that requests game restart.
     */
    public void restartGame() {
        restartGame.emit();
    }

    /**
     * Connects a listener to the play-selected event.
     *
     * @param runnable callback invoked when the player chooses to play
     */
    public void playSelectedConnect(Runnable runnable) { playSelected.connect(runnable); }

    /**
     * Connects a listener to the quit-selected event.
     *
     * @param runnable callback invoked when the player chooses to quit
     */
    public void quitSelectedConnect(Runnable runnable) { quitSelected.connect(runnable); }

    /**
     * Connects a listener to the instructions-selected event.
     *
     * @param runnable callback invoked when the player requests instructions
     */
    public void instructionsSelectedConnect(Runnable runnable) { instructionsSelected.connect(runnable); }

    /**
     * Connects a listener to the menu start event.
     *
     * @param runnable callback invoked when the main menu is shown
     */
    public void startMenuConnect(Runnable runnable) { startMenu.connect(runnable); }

    /**
     * Connects a listener to the lose event.
     *
     * @param runnable callback invoked when the player loses
     */
    public void playerLoseConnect(Runnable runnable) { playerLose.connect(runnable); }

    /**
     * Connects a listener to the restart game event.
     *
     * @param runnable callback invoked when the player restarts the game
     */
    public void restartGameConnect(Runnable runnable) { restartGame.connect(runnable); }
}