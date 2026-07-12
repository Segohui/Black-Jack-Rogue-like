package blackjack.view.terminal.screens.battle;

import java.util.ArrayList;
import java.util.List;

import blackjack.dtos.core.battle.CombatOverDTO;
import blackjack.view.InputOutput;
import blackjack.view.terminal.screens.Screen;

public class CombatOverScreen implements Screen {
    private final InputOutput io;
    private final CombatOverDTO winnerDataDTO;

    public CombatOverScreen(InputOutput io, CombatOverDTO winnerDataDTO) {
        this.io = io;
        this.winnerDataDTO = winnerDataDTO;
    }

    @Override
    public void render() {
        List<String> messages = new ArrayList<>();

        messages.add("Winner: " + winnerDataDTO.winnerName());

        if (winnerDataDTO.isPlayerControlled()) {
            messages.add("Money gained: " + winnerDataDTO.goldReward());
        }

        io.printLine();
        io.printHeader("Game End");
        io.printUpdate(messages, 20);
        io.enterToProceed();
    }
}
