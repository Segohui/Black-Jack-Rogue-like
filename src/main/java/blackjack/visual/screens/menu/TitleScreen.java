package blackjack.visual.screens.menu;

import blackjack.controller.MenuController;
import blackjack.visual.InputOutput;
import blackjack.visual.screens.Screen;
import blackjack.visual.terminal.ActionPrompter;

public class TitleScreen implements Screen {
    private final InputOutput io;
    private final MenuController controller;

    public TitleScreen(InputOutput io, MenuController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printHeader("Black Jack Rogue Like", 30);
        
        ActionPrompter actionPrompter = new ActionPrompter(io);
        actionPrompter.addAction("Start Game", controller::selectPlay);
        actionPrompter.addAction("Exit", controller::selectQuit);
        actionPrompter.promptAndRun();
    }
}