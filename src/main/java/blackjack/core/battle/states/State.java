package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.exceptions.InvalidTurnActionException;

public interface State {
    void handle(BattleCore core);

    default void hit(BattleCore core) {
        throw new InvalidTurnActionException("Invalid command");
    }

    default void stand(BattleCore core) {
        throw new InvalidTurnActionException("Invalid command");
    }

    default void useBoughtCard(BattleCore core, int idx){
        throw new InvalidTurnActionException("Invalid command");
    }
}
