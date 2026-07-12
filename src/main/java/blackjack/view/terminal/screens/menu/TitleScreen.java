package blackjack.view.terminal.screens.menu;

import blackjack.controller.MenuController;
import blackjack.view.InputOutput;
import blackjack.view.terminal.ActionPrompter;
import blackjack.view.terminal.screens.Screen;

public class TitleScreen implements Screen {
    private final InputOutput io;
    private final MenuController controller;

    public TitleScreen(InputOutput io, MenuController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printHeader("Black Jack Rogue Like");
        
        ActionPrompter actionPrompter = new ActionPrompter(io);
        actionPrompter.addAction("Start Game", controller::selectPlay);
        actionPrompter.addAction("Instructions", controller::selectInstructions);
        actionPrompter.defineBottomAction("Exit", controller::selectQuit);
        actionPrompter.promptAndRun();
    }
}