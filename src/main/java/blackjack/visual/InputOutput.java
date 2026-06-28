package blackjack.visual;

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

    public void printDivider(int size) {
        printMessage("-".repeat(size));
    }

    public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}
}
