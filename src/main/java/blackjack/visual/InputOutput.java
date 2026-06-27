package blackjack.visual;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputOutput {
    private final Scanner scanner;

    public InputOutput() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        try {
            String line = scanner.nextLine().strip();
            return line;
        } catch (IllegalStateException e) {
            printError(e.getMessage());
        } catch (NoSuchElementException e) {
            printError(e.getMessage());
        }

        return null;
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
}
