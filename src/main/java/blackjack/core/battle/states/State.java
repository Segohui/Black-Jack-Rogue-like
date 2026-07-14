package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.exceptions.InvalidTurnActionException;

/**
 * Represents a battle state in the turn-based combat state machine.
 *
 * <p>Conforming to the state pattern, concrete implementations define how
 * combat actions behave in each turn phase.</p>
 */
public interface State {
    /**
     * Handles the current battle state logic and may transition the core.
     *
     * @param core current battle core instance
     */
    void handle(BattleCore core);

    /**
     * Attempts a hit action in the current state.
     *
     * @param core current battle core instance
     * @throws InvalidTurnActionException when the action is invalid in this state
     */
    default void hit(BattleCore core) {
        throw new InvalidTurnActionException("Invalid command");
    }

    /**
     * Attempts a stand action in the current state.
     *
     * @param core current battle core instance
     * @throws InvalidTurnActionException when the action is invalid in this state
     */
    default void stand(BattleCore core) {
        throw new InvalidTurnActionException("Invalid command");
    }

    /**
     * Attempts to use an item in the current state.
     *
     * @param core current battle core instance
     * @param idx item index to use
     * @throws InvalidTurnActionException when the action is invalid in this state
     */
    default void useItem(BattleCore core, int idx){
        throw new InvalidTurnActionException("Invalid command");
    }
}
