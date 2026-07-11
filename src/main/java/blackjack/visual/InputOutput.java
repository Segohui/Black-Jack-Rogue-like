package blackjack.visual;

import java.util.List;
import java.util.Scanner;

import blackjack.dto.EntityStateData;

public class InputOutput {
    private final Scanner scanner;

    public InputOutput() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        System.out.print("> ");
        String line = scanner.nextLine();
        return line;
    }

    public String getCleanInput() {
        System.out.print("> ");
        String line = scanner.nextLine().strip().toLowerCase();
        return line;
    }

    public void printError(String message) {
        System.err.println("[ERROR] " + message);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printInline(String message) {
        System.out.print(message);
    }
    public void printColored(String message, String color) {
        System.out.println(color + message + ConsoleColors.RESET);
    }

    private void printColoredInLine(String message, String color) {
        System.out.print(color + message + ConsoleColors.RESET);
    }

    public void printUpdate(String message) {
        printMessage("> " + message);
    }

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

    public void printDivider(String divider) {
        printMessage(divider.repeat(20));
    }

    public void printHand(List<String> cardNames) {
        for (String cardName : cardNames) {
            printMessage(cardName + " ");
        }
        printMessage("");
    }

    public void printEntityState(EntityStateData entityData) {
        printColored(entityData.name() + " 's hand (" + entityData.currentSum() + "): ", ConsoleColors.CYAN);
        printColored("Hp: " + entityData.hp(), ConsoleColors.RED);
        printDivider("-");
        printMessage("Hand:");
        printHand(entityData.cardNames());
    }

    public void printLine() {
        printMessage("");
    }

    public void enterToProceed() {
        printMessage("\nEnter to proceed...");
        getInput();
    }

    public void clearScreen() {
        for (int i = 0; i < 30; i++) {
            printMessage("");
        }
    }
}
