package blackjack.core.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import blackjack.core.cards.Card;
import blackjack.core.cards.Deck;
import blackjack.core.cards.enums.Rank;
import blackjack.core.cards.enums.Suit;
import blackjack.core.inventory.Inventory;
import blackjack.core.inventory.Item;
import blackjack.core.inventory.ItemRegistry;
import blackjack.core.shop.buyables.CardBuyable;
import blackjack.core.shop.buyables.ItemBuyable;

public class BuyableFactory {
    private static final int CARD_COST = 10;

    private final Random random = new Random();
    private final Inventory inventory;
    private final Deck deck;
    private final ItemRegistry itemRegistry;

    public BuyableFactory(Inventory inventory, Deck deck, ItemRegistry itemRegistry) {
        this.inventory = inventory;
        this.deck = deck;
        this.itemRegistry = itemRegistry;
    }

    public List<Buyable> generateCardBuyables(int amount) {
        List<Buyable> cardBuyables = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            cardBuyables.add(generateRandomCardBuyable());
        }

        return cardBuyables;
    }

    private Buyable generateRandomCardBuyable() {
        Rank[] ranks = Rank.values();
        Suit[] suits = Suit.values();

        Rank randomRank = ranks[random.nextInt(ranks.length)];
        Suit randomSuit = suits[random.nextInt(suits.length)];

        Card card = new Card(randomRank, randomSuit);
        return new CardBuyable(card, CARD_COST, deck);
    }

    public List<Buyable> generateItemBuyables(int amount) {
        List<Buyable> itemBuyables = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            itemBuyables.add(generateRandomItemBuyable());
        }

        return itemBuyables;
    }

    private Buyable generateRandomItemBuyable() {
        Item item = itemRegistry.createNextItem();
        return new ItemBuyable(item, 12, inventory);
    }
}