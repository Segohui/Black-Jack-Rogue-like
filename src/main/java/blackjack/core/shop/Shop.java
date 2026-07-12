package blackjack.core.shop;

import java.util.ArrayList;
import java.util.List;

import blackjack.core.cards.Deck;
import blackjack.core.inventory.Inventory;
import blackjack.core.inventory.ItemRegistry;
import blackjack.exceptions.InsufficientGoldException;

public class Shop {
    private final List<Buyable> itemsForSale;
    private final Inventory inventory;
    private final BuyableFactory buyableFactory;

    public Shop(Inventory inventory, Deck deck, ItemRegistry itemRegistry) {
        this.itemsForSale = new ArrayList<>();
        this.inventory = inventory;
        this.buyableFactory = new BuyableFactory(inventory, deck, itemRegistry);
    }

    public void clearItems() {
        itemsForSale.clear();
    }

    public void populateWithItems() {
        itemsForSale.addAll(buyableFactory.generateCardBuyables(3));
        itemsForSale.addAll(buyableFactory.generateItemBuyables(3));
    }

    public List<Buyable> getItemsForSale() {
        return itemsForSale;
    }

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
}