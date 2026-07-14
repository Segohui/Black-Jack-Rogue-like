package blackjack.exceptions;

/**
 * Raised when an item is used after it should no longer be available.
 */
public class DeadItemException extends RuntimeException {
    public DeadItemException(String message) {
        super(message);
    }
}
