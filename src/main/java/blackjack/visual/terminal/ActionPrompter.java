package blackjack.visual.terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.visual.InputOutput;

public class ActionPrompter {
    private final List<String> options = new ArrayList<>();
    private final Map<String, Runnable> optionToAction = new HashMap<>();
    private final InputOutput io;

    public ActionPrompter(InputOutput io) {
        this.io = io;
    }

    public void addAction(String name, Runnable runnable) {
        options.add(name);
        optionToAction.put(name, runnable);
    }

    public void promptAndRun() {
        io.printMessage("Choose by number:");
        for (int i = 0; i < options.size(); i++) {
            io.printMessage("[%d] %s".formatted(i + 1, options.get(i)));
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
            if (choiceNumber < 1 || choiceNumber > options.size()) {
                io.printMessage("Enter a valid choice number.");
                continue;
            }
            break;
        }
        String choice = options.get(choiceNumber - 1);
        optionToAction.get(choice).run();
    }
}
