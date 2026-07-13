package blackjack.view.screens.menu;

import blackjack.view.InputOutput;
import blackjack.view.screens.Screen;

public class InstructionsScreen implements Screen {
    private final InputOutput io;

    public InstructionsScreen(InputOutput io) {
        this.io = io;
    }

    @Override
    public void render() {
        io.printHeader("Game Instructions");
        
        io.printDivider("-", 70);
        io.printMessage("1. OBJECTIVE: Survive a series of combats with various enemies");
        io.printLine();
        io.printMessage("2. COMBAT MECHANIC: Play Black Jack turns, trying to achieve");
        io.printMessage("   the closest to the limit (21 standard) without busting.");
        io.printLine();
        io.printMessage("3. DAMAGE: the total sum value of the winners");
        io.printMessage("   hand, modified by item effects/activations.");
        io.printMessage("   (if standing, you receive half the damage)");
        io.printLine();
        io.printMessage("4. REWARDS: Defeated enemies drop gold to be spent ");
        io.printMessage("   in the shop for buying items");
        io.printLine();
        io.printMessage("5. GAME END: If your HP reaches zero, you die.");
        io.printDivider("-", 70);

        io.enterToProceed();
    }
}