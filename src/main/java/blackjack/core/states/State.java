package blackjack.core.states;

import blackjack.core.BlackjackCore;
import blackjack.exceptions.InvalidTurnActionException;

public interface State {
    void handle(BlackjackCore core);

    default void hit(BlackjackCore core) {
        throw new InvalidTurnActionException("Invalid command");
    }

    default void stand(BlackjackCore core) {
        throw new InvalidTurnActionException("Invalid command");
    }

    default void useBoughtCard(BlackjackCore core, int idx){
        throw new InvalidTurnActionException("Invalid command");
    }
}
