package blackjack.dto;

import java.util.List;

public record EntityStateData(String name, int currentSum, List<String> cardNames) {}
