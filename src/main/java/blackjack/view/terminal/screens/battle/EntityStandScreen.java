package blackjack.view.terminal.screens.battle;

import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;

public class EntityStandScreen implements Screen {
    private final InputOutput io;
    private final String entityName;

    public EntityStandScreen(InputOutput io, String entityName) {
        this.io = io;
        this.entityName = entityName;
    }

    public void render() {
        io.printLine();
        io.printUpdate(entityName + " stand! (will take half damage)");
        io.enterToProceed();
    }
}