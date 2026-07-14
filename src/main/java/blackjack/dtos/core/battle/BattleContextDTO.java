package blackjack.dtos.core.battle;

import blackjack.core.cards.Deck;
import blackjack.core.entity.capabilities.IRoundParticipant;

/**
 * Carries the shared context required for item or battle actions.
 *
 * @param player active player participant
 * @param enemy opposing participant
 * @param playerDeck deck used by the player
 */
public record BattleContextDTO(IRoundParticipant player, IRoundParticipant enemy, Deck playerDeck) {}
