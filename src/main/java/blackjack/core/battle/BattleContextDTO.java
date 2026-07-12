package blackjack.core.battle;

import blackjack.core.cards.Deck;
import blackjack.core.entity.Entity;

public record BattleContextDTO(Entity player, Entity enemy, Deck playerDeck) {}
