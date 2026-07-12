package blackjack.core.shop;

import java.util.ArrayList;
import java.util.List;

import blackjack.core.cards.Deck;
import blackjack.core.inventory.Inventory;
import blackjack.core.inventory.ItemRegistry;

public class Shop {
    private final List<Buyable> itemsForSale;
    private final Inventory inventory;
    private final BuyableFactory buyableFactory;

    public Shop(Inventory inventory, Deck deck, ItemRegistry itemRegistry) {
        this.itemsForSale = new ArrayList<>();
        this.inventory = inventory;
        this.buyableFactory = new BuyableFactory(inventory, deck, itemRegistry);
    }

    public void populateWithItems(int amount) {
        itemsForSale.clear();
        itemsForSale.addAll(buyableFactory.generateCardBuyables(amount));
        itemsForSale.addAll(buyableFactory.generateItemBuyables(amount));
    }

    public List<Buyable> getItemsForSale() {
        return itemsForSale;
    }

    public boolean buy(int index) {
        if (index < 0 || index >= itemsForSale.size()) {
            return false;
        }

        Buyable item = itemsForSale.get(index);
        if (!inventory.canAfford(item.getCost())) {
            return false;
        }

        inventory.spendGold(item.getCost());
        item.buy();
        itemsForSale.remove(index);
        return true;
    }
}