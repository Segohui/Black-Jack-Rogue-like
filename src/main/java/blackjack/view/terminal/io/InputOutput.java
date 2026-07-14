package blackjack.view.terminal.io;

import java.util.List;
import java.util.Scanner;

import blackjack.dtos.core.battle.EntityStateDTO;

/**
 * Handles terminal input and output for the game.
 *
 * <p>This class centralizes console I/O operations, keeping the view layer
 * separated from direct {@code System} calls.</p>
 */
public class InputOutput {
    private final Scanner scanner;

    /**
     * Creates an input/output provider using standard console input.
     */
    public InputOutput() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a raw line from standard input.
     *
     * @return input line from the user
     */
    public String getInput() {
        System.out.print("> ");
        String line = scanner.nextLine();
        return line;
    }

    /**
     * Reads a cleaned line from standard input.
     *
     * @return trimmed lowercase input line
     */
    public String getCleanInput() {
        System.out.print("> ");
        String line = scanner.nextLine().strip().toLowerCase();
        return line;
    }

    /**
     * Prints an error message to the error stream.
     *
     * @param message error message to show
     */
    public void printError(String message) {
        System.err.println("[ERROR] " + message);
    }

    /**
     * Prints a normal line of output.
     *
     * @param message message to show
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message without a trailing newline.
     *
     * @param message message to show inline
     */
    public void printInline(String message) {
        System.out.print(message);
    }

    /**
     * Prints a colored message followed by a newline.
     *
     * @param message message to show
     * @param color console color code
     */
    public void printColored(String message, String color) {
        System.out.println(color + message + ConsoleColors.RESET);
    }

    /**
     * Prints a colored message without a newline.
     *
     * @param message message to show
     * @param color console color code
     */
    private void printColoredInLine(String message, String color) {
        System.out.print(color + message + ConsoleColors.RESET);
    }

    /**
     * Prints an update section with an emphasis style.
     *
     * @param message value to display inside the update box
     */
    public void printUpdate(String message) {
        String m = "> " + message;

        printDivider("-", m.length());
        printColored(m, ConsoleColors.YELLOW);
        printDivider("-", m.length());
    }

    /**
     * Prints several update messages inside a framed block.
     *
     * @param messages messages to print
     * @param size width of the surrounding divider
     */
    public void printUpdate(List<String> messages, int size) {
        printDivider("-", size);
        for (String message: messages) {
            String m = "> " + message;
            printColored(m, ConsoleColors.YELLOW);
        }
        printDivider("-", size);
    }

    /**
     * Prints a header block with a colored title.
     *
     * @param message title text to display
     */
    public void printHeader(String message) {
        printDivider("=", message.length() + 4);
        printInline("| ");
        printColoredInLine(message, ConsoleColors.CYAN);
        printMessage(" |");
        printDivider("=", message.length() + 4);
    }

    public void printDivider(String divider, int size) {
        printMessage(divider.repeat(size));
    }

    /**
     * Prints a repeated divider line.
     *
     * @param divider character(s) used to draw the divider
     */
    public void printDivider(String divider) {
        printMessage(divider.repeat(20));
    }

    /**
     * Prints a list of card names as a simple hand representation.
     *
     * @param cardNames cards to print
     */
    public void printHand(List<String> cardNames) {
        for (String cardName : cardNames) {
            printMessage(cardName + " ");
        }
        printMessage("");
    }

    /**
     * Prints a formatted entity state summary.
     *
     * @param entityData state snapshot for the entity
     */
    public void printEntityState(EntityStateDTO entityData) {
        printColored(entityData.name() + "'s hand (" + entityData.currentSum() + "): ", ConsoleColors.CYAN);
        printColored("Hp: " + entityData.hp(), ConsoleColors.RED);
        printDivider("-");
        printMessage("Hand:");
        printHand(entityData.cardNames());
    }

    /**
     * Prints a blank line to separate sections.
     */
    public void printLine() {
        System.out.println();
    }

    /**
     * Prompts the user to press Enter before continuing.
     */
    public void enterToProceed() {
        printMessage("\nEnter to proceed...");
        getInput();
    }

    /**
     * Clears the terminal screen by printing new lines.
     */
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
