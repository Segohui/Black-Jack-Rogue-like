package blackjack.dtos.core.battle;

/**
 * Carries the result of a completed combat encounter.
 *
 * @param winnerName name of the victor
 * @param isPlayerControlled whether the victor is the player
 * @param goldReward reward granted after the combat
 */
public record CombatOverDTO(String winnerName, boolean isPlayerControlled, int goldReward) {}
