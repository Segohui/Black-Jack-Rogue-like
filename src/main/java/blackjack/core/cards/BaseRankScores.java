package blackjack.core.cards;

import java.util.EnumMap;
import java.util.Map;

import blackjack.core.cards.enums.Rank;

public class BaseRankScores {
    private static final Map<Rank, Integer> rankScores = new EnumMap<>(Map.ofEntries(
        Map.entry(Rank.RANK_2, 2),
        Map.entry(Rank.RANK_3, 3),
        Map.entry(Rank.RANK_4, 4),
        Map.entry(Rank.RANK_5, 5),
        Map.entry(Rank.RANK_6, 6),
        Map.entry(Rank.RANK_7, 7),
        Map.entry(Rank.RANK_8, 8),
        Map.entry(Rank.RANK_9, 9),
        Map.entry(Rank.RANK_10, 10),
        Map.entry(Rank.RANK_J, 10),
        Map.entry(Rank.RANK_Q, 10),
        Map.entry(Rank.RANK_K, 10),
        Map.entry(Rank.RANK_A, 11)
    ));

    public static int getScore(Rank rank) {
        return rankScores.get(rank);
    }
}
