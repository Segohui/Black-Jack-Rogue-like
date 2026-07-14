package blackjack.view.terminal.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Prompts the player to choose from a set of numbered actions.
 *
 * <p>This helper separates input validation and option dispatch from the view
 * rendering code.</p>
 */
public class ActionPrompter {
    private final List<String> options = new ArrayList<>();
    private final Map<String, Runnable> optionToAction = new HashMap<>();
    private final InputOutput io;
    private String bottomOption = null;
    private Runnable bottomAction = null;

    /**
     * Creates a new action prompter that writes prompts to the provided I/O.
     *
     * @param io input/output provider for prompt display and user input
     */
    public ActionPrompter(InputOutput io) {
        this.io = io;
    }

    /**
     * Registers a selectable action.
     *
     * @param name label shown to the player
     * @param runnable action executed when selected
     */
    public void addAction(String name, Runnable runnable) {
        options.add(name);
        optionToAction.put(name, runnable);
    }

    /**
     * Defines a special action available at the bottom of the option list.
     *
     * @param name label shown for the bottom action
     * @param runnable action executed when the bottom option is selected
     */
    public void defineBottomAction(String name, Runnable runnable) {
        bottomOption = name;
        bottomAction = runnable;
    }

    /**
     * Prompts the player until a valid numeric choice is made, then executes it.
     */
    public void promptAndRun() {
        io.printMessage("Choose by number:");
        for (int i = 0; i < options.size(); i++) {
            io.printMessage("[%d] %s".formatted(i + 1, options.get(i)));
        }
        if (bottomOption != null) {
            io.printMessage("[%d] %s".formatted(0, bottomOption));
        }
        int choiceNumber;
        while (true) {
            String line = io.getInput();
            try {
                choiceNumber = Integer.parseInt(line);
            } catch (IllegalArgumentException e) {
                io.printMessage("Type the NUMBER of the choice.");
                continue;
            }
            if (choiceNumber < ((bottomAction == null) ? 1 : 0)
                    || choiceNumber > options.size()) {
                io.printMessage("Enter a valid choice number.");
                continue;
            }
            break;
        }
        if (choiceNumber == 0) {
            bottomAction.run();
            return;
        }
        String choice = options.get(choiceNumber - 1);
        optionToAction.get(choice).run();
    }
}
