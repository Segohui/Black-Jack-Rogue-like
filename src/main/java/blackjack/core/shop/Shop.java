package blackjack.core.shop;

import java.util.ArrayList;
import java.util.List;

import blackjack.core.cards.Deck;
import blackjack.core.inventory.Inventory;
import blackjack.core.inventory.ItemRegistry;
import blackjack.dtos.core.items.ShopItemDTO;
import blackjack.exceptions.InsufficientGoldException;

/**
 * Represents the shop domain, providing item generation and purchase logic.
 *
 * <p>This class contains the current buyable list and delegates creation to
 * {@link BuyableFactory}. It enforces business rules during purchase.
 * </p>
 */
public class Shop {
    private final List<Buyable> itemsForSale;
    private final Inventory inventory;
    private final BuyableFactory buyableFactory;

    /**
     * Creates the shop with a shared inventory and item registry.
     *
     * @param inventory shared player inventory used for purchases
     * @param deck deck used to generate buyable cards
     * @param itemRegistry registry used to resolve item definitions
     */
    public Shop(Inventory inventory, Deck deck, ItemRegistry itemRegistry) {
        this.itemsForSale = new ArrayList<>();
        this.inventory = inventory;
        this.buyableFactory = new BuyableFactory(inventory, deck, itemRegistry);
    }

    /**
     * Clears the current list of shop items.
     */
    public void clearItems() {
        itemsForSale.clear();
    }

    /**
     * Populates the shop with a fresh set of card and item buyables.
     */
    public void populateWithItems() {
        itemsForSale.addAll(buyableFactory.generateCardBuyables(3));
        itemsForSale.addAll(buyableFactory.generateItemBuyables(3));
    }

    /**
     * Processes a purchase request for an item in the shop.
     *
     * @param index index of the item to buy
     * @return true when the purchase succeeds
     * @throws InsufficientGoldException when the player does not have enough gold
     */
    public boolean buy(int index) throws InsufficientGoldException {
        if (index < 0 || index >= itemsForSale.size()) {
            return false;
        }

        Buyable item = itemsForSale.get(index);
        if (!inventory.canAfford(item.getCost())) {
            throw new InsufficientGoldException("Not enough gold to buy " + item.getName()); 
        }

        inventory.spendGold(item.getCost());
        item.buy();
        itemsForSale.remove(index);
        return true;
    }

    /**
     * Returns a list of DTO view models for the shop items currently available.
     *
     * @return list of shop item DTOs
     */
    public List<ShopItemDTO> getShopItems() {
        return itemsForSale.stream()
                .map(buyable -> new ShopItemDTO(
                        buyable.getName(), 
                        buyable.getItemType(), 
                        buyable.getDescription(), 
                        buyable.getCost()
                ))
                .toList();
    }
}