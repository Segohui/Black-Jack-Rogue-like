package blackjack.core.cards;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import blackjack.core.cards.enums.Rank;

/**
 * Calculates a hand total while handling aces as flexible values.
 */
public class HandEvaluator {
    private final Map<Rank, Integer> rankValues = new EnumMap<>(Rank.class);

    public HandEvaluator() {
        rankValues.put(Rank.RANK_A, 1);
        rankValues.put(Rank.RANK_2, 2);
        rankValues.put(Rank.RANK_3, 3);
        rankValues.put(Rank.RANK_4, 4);
        rankValues.put(Rank.RANK_5, 5);
        rankValues.put(Rank.RANK_6, 6);
        rankValues.put(Rank.RANK_7, 7);
        rankValues.put(Rank.RANK_8, 8);
        rankValues.put(Rank.RANK_9, 9);
        rankValues.put(Rank.RANK_10, 10);
        rankValues.put(Rank.RANK_J, 10);
        rankValues.put(Rank.RANK_Q, 10);
        rankValues.put(Rank.RANK_K, 10);
    }

    public int calculateSum(List<Card> cards) {
        int sum = 0;
        int aces = 0;
        for (Card card : cards) {
            Rank rank = card.getRank();
            if (rank == Rank.RANK_A) {
                aces++;
            }
            sum += rankValues.get(rank);
        }
        for (int i = 0; i < aces; i++) {
            if (sum + 10 <= 21) {
                sum += 10;
            }
        }
        return sum;
    }
}
