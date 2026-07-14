package blackjack.exceptions;

/**
 * Raised when an invalid action is attempted during a turn.
 */
public class InvalidTurnActionException extends RuntimeException {
    public InvalidTurnActionException(String message) {
        super(message);
    }
}
