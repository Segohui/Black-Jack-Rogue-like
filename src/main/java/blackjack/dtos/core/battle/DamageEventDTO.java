package blackjack.dtos.core.battle;

/**
 * Carries information about a damage event emitted during battle.
 *
 * @param targetName entity that received the damage
 * @param damage amount of damage dealt
 */
public record DamageEventDTO(String targetName, int damage) {}
