package blackjack.view.terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.view.InputOutput;

public class ActionPrompter {
    private final List<String> options = new ArrayList<>();
    private final Map<String, Runnable> optionToAction = new HashMap<>();
    private final InputOutput io;
    private String bottomOption = null;
    private Runnable bottomAction = null;

    public ActionPrompter(InputOutput io) {
        this.io = io;
    }

    public void addAction(String name, Runnable runnable) {
        options.add(name);
        optionToAction.put(name, runnable);
    }

    public void defineBottomAction(String name, Runnable runnable) {
        bottomOption = name;
        bottomAction = runnable;
    }

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
