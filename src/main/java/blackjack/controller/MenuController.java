package blackjack.controller;

import blackjack.core.EmptySignal;

public class MenuController {
    private final EmptySignal playSelected = new EmptySignal();
    private final EmptySignal quitSelected = new EmptySignal();
    private final EmptySignal instructionsSelected = new EmptySignal();
    private final EmptySignal startMenu = new EmptySignal();

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

    public void playSelectedConnect(Runnable runnable) { playSelected.connect(runnable); }
    public void quitSelectedConnect(Runnable runnable) { quitSelected.connect(runnable); }
    public void instructionsSelectedConnect(Runnable runnable) { instructionsSelected.connect(runnable); }
    public void startMenuConnect(Runnable runnable) {startMenu.connect(runnable); }
}