package blackjack.visual;

import java.util.List;
import java.util.Scanner;

public class InputOutput {
    private final Scanner scanner;

    public InputOutput() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        String line = scanner.nextLine().strip();
        return line;
    }

    public void printError(String message) {
        System.err.println("[ERROR] " + message);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printUpdate(String message) {
        System.out.println("> " + message);
    }

    public void printHeader(String message, int size) {
        System.out.println("=".repeat(size));
        printMessage("| " + message + "| ");
        System.out.println("=".repeat(size));

    }

    public void printDivider(int size) {
        printMessage("-".repeat(size));
    }

    public void printDivider() {
        printMessage("-".repeat(15));
    }

    public void printHand(List<String> cardNames) {
        for (String card : cardNames) {
            printMessage(card + " ");
        }
        printMessage("");
    }

    public void printLine() {
        printMessage("");
    }

    public void enterToProceed() {
        printMessage("\nEnter to proceed...");
        getInput();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
