package blackjack.visual.terminal.screens.battle;

import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

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