package blackjack.core.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import blackjack.core.cards.Card;
import blackjack.core.cards.PlayingCardFactory;
import blackjack.core.cards.enums.Rank;
import blackjack.core.cards.enums.Suit;
import blackjack.core.shop.items.CardItem;

public class ShopItemFactory {
    private static final int CARD_COST = 10;

    private final PlayingCardFactory cardFactory = new PlayingCardFactory();
    private final Random random = new Random();

    public List<ShopItem> generateShopItems(int amount) {
        List<ShopItem> items = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            items.add(generateRandomCardItem());
        }

        return items;
    }

    private ShopItem generateRandomCardItem() {
        Rank[] ranks = Rank.values();
        Suit[] suits = Suit.values();

        Rank randomRank = ranks[random.nextInt(ranks.length)];
        Suit randomSuit = suits[random.nextInt(suits.length)];

        Card card = cardFactory.createCard(randomRank, randomSuit);
        return new CardItem(card, CARD_COST);
    }
}