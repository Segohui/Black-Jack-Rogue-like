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

/**
 * Factory responsible for generating buyable shop items.
 *
 * <p>This class encapsulates the variability of shop inventory and keeps
 * the shop domain open for extension by allowing new buyable generation
 * strategies without changing the shop itself.</p>
 */
public class BuyableFactory {
    private static final int CARD_COST = 3;

    private final Random random = new Random();
    private final Inventory inventory;
    private final Deck deck;
    private final ItemRegistry itemRegistry;

    /**
     * Creates a buyable factory for the shop.
     *
     * @param inventory shared player inventory
     * @param deck deck used to generate card buyables
     * @param itemRegistry registry used to create item instances
     */
    public BuyableFactory(Inventory inventory, Deck deck, ItemRegistry itemRegistry) {
        this.inventory = inventory;
        this.deck = deck;
        this.itemRegistry = itemRegistry;
    }

    /**
     * Generates a list of card buyables for the shop.
     *
     * @param amount number of card buyables to generate
     * @return list of card buyables
     */
    public List<Buyable> generateCardBuyables(int amount) {
        List<Buyable> cardBuyables = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            cardBuyables.add(generateRandomCardBuyable());
        }

        return cardBuyables;
    }

    /**
     * Generates a single random card buyable.
     *
     * @return random card buyable
     */
    private Buyable generateRandomCardBuyable() {
        Rank[] ranks = Rank.values();
        Suit[] suits = Suit.values();

        Rank randomRank = ranks[random.nextInt(ranks.length)];
        Suit randomSuit = suits[random.nextInt(suits.length)];

        Card card = new Card(randomRank, randomSuit);
        return new CardBuyable(card, CARD_COST, deck);
    }

    /**
     * Generates a list of item buyables for the shop.
     *
     * @param amount number of item buyables to generate
     * @return list of item buyables
     */
    public List<Buyable> generateItemBuyables(int amount) {
        List<Buyable> itemBuyables = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            itemBuyables.add(generateRandomItemBuyable());
        }

        return itemBuyables;
    }

    /**
     * Generates a random item buyable based on the registry.
     *
     * @return random item buyable
     */
    private Buyable generateRandomItemBuyable() {
        Item item = itemRegistry.createNextItem();
        return new ItemBuyable(item, inventory);
    }
}