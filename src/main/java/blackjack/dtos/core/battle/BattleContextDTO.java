package blackjack.dtos.core.battle;

import blackjack.core.cards.Deck;
import blackjack.core.entity.capabilities.Entity;

public record BattleContextDTO(Entity player, Entity enemy, Deck playerDeck) {}
