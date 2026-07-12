package blackjack.dtos.core.battle;

import java.util.List;

public record EntityStateDTO(String name, int currentSum, List<String> cardNames, int hp) {}
