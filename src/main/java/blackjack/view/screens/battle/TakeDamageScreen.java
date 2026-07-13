package blackjack.view.screens.battle;

import blackjack.dtos.core.battle.DamageEventDTO;
import blackjack.view.InputOutput;
import blackjack.view.screens.Screen;

public class TakeDamageScreen implements Screen {
    private final InputOutput io;
    private final DamageEventDTO eventDTO;

    public TakeDamageScreen(InputOutput io, DamageEventDTO eventDTO) {
        this.io = io;
        this.eventDTO = eventDTO;
    }

    public void render() {
        String message = eventDTO.targetName() + " took " + eventDTO.damage() + " damage!";
        
        io.printLine();
        io.printUpdate(message);
        io.enterToProceed();
    }
}