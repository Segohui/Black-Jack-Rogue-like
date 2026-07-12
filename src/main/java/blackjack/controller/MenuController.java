package blackjack.controller;

import blackjack.core.signal.EmptySignal;

public class MenuController {
    private final EmptySignal playSelected = new EmptySignal();
    private final EmptySignal quitSelected = new EmptySignal();
    private final EmptySignal playerLose = new EmptySignal();
    private final EmptySignal instructionsSelected = new EmptySignal();
    private final EmptySignal startMenu = new EmptySignal();
    private final EmptySignal restartGame = new EmptySignal();

    private final EmptySignal optionsSelected = new EmptySignal(); // If we add options (clear screen on/off)

    public void startMenu() {
        startMenu.emit();
    }

    public void selectInstructions() {
        instructionsSelected.emit();
    }

    public void selectPlay() {
        playSelected.emit();
    }

    public void selectQuit() {
        quitSelected.emit();
    }

    public void selectLose() {
        playerLose.emit();
    }

    public void restartGame() {
        restartGame.emit();
    }

    public void playSelectedConnect(Runnable runnable) { playSelected.connect(runnable); }
    public void quitSelectedConnect(Runnable runnable) { quitSelected.connect(runnable); }
    public void instructionsSelectedConnect(Runnable runnable) { instructionsSelected.connect(runnable); }
    public void startMenuConnect(Runnable runnable) { startMenu.connect(runnable); }
    public void playerLoseConnect(Runnable runnable) { playerLose.connect(runnable); }
    public void restartGameConnect(Runnable runnable) { restartGame.connect(runnable); }
}