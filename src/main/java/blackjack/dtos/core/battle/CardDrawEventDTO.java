package blackjack.dtos.core.battle;

/**
 * Carries information about a card draw event emitted during battle.
 *
 * @param cardName name of the drawn card
 * @param entityName entity that drew the card
 */
public record CardDrawEventDTO(String cardName, String entityName) {}
