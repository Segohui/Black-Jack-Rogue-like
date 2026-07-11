package blackjack.controller;

import blackjack.core.EmptySignal;

public class MenuController {
    private final EmptySignal playSelected = new EmptySignal();
    private final EmptySignal optionsSelected = new EmptySignal(); // If we add options (clear screen on/off)
    private final EmptySignal quitSelected = new EmptySignal();

    public void selectPlay() {
        playSelected.emit();
    }

    public void selectQuit() {
        quitSelected.emit();
    }

    public void playSelectedConnect(Runnable runnable) { playSelected.connect(runnable); }
    public void quitSelectedConnect(Runnable runnable) { quitSelected.connect(runnable); }
}