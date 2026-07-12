package blackjack.core.battle;

import blackjack.core.cards.Deck;
import blackjack.entity.Entity;

public record BattleContextDTO(Entity player, Entity enemy, Deck playerDeck) {}
