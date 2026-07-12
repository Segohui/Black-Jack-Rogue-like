package blackjack.visual.terminal.screens.menu;

import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

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
        io.printMessage("\n2. COMBAT MECHANIC: Play Black Jack turns, trying to achieve");
        io.printMessage("   the closest to the limit (21 standart) without busting.");
        io.printMessage("\n3. DAMAGE: the total sum value of the winners");
        io.printMessage("   hand plus items effects activation.");
        io.printMessage("   (if standing, you recieve half the damage)");
        io.printMessage("\n4. REWARDS: Defeated enemies drops gold to be used ");
        io.printMessage("   in the shop for buying items");
        io.printMessage("\n5. GAME END: If your HP reaches zero, you die.");
        io.printDivider("-", 70);

        io.enterToProceed();
    }
}