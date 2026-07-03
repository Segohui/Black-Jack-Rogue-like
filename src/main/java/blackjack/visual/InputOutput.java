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

    public void printUpdate(String message) {
        printMessage("> " + message);
    }

    public void printHeader(String message, int size) {
        printDivider("=", size);
        printMessage("| " + message + " |");
        printDivider("=", size);
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
        printMessage(entityData.name() + " 's hand (" + entityData.currentSum() + "): ");
        printMessage("Hp: " + entityData.hp());
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
        for (int i = 0; i < 50; i++) {
            printMessage("");
        }
    }
}
