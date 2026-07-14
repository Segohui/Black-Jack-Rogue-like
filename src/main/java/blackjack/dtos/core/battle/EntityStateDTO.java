package blackjack.dtos.core.battle;

import java.util.List;

/**
 * Represents the visual state of an entity for terminal rendering.
 *
 * @param name entity name
 * @param currentSum current hand total
 * @param cardNames card names currently in hand
 * @param hp current hit points
 */
public record EntityStateDTO(String name, int currentSum, List<String> cardNames, int hp) {}
