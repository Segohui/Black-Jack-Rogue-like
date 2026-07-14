package blackjack.exceptions;

/**
 * Raised when a purchase is attempted without enough gold.
 */
public class InsufficientGoldException extends Exception {
    public InsufficientGoldException(String message){
        super(message);    
    }
}
