package blackjack.dtos.core.battle;

import blackjack.core.cards.Deck;
import blackjack.core.entity.capabilities.IRoundParticipant;

public record BattleContextDTO(IRoundParticipant player, IRoundParticipant enemy, Deck playerDeck) {}
