package blackjack.exceptions;

public class InvalidTurnActionException extends RuntimeException {
    public InvalidTurnActionException(String message) {
        super(message);
    }
}
