package blackjack.core.shop.buyables;

import blackjack.core.inventory.Inventory;
import blackjack.core.inventory.Item;
import blackjack.core.shop.Buyable;

public class ItemBuyable implements Buyable {
    private final Item item;
    private final int cost;
    private final Inventory inventory;

    public ItemBuyable(Item item, int cost, Inventory inventory) {
        this.item = item;
        this.cost = cost;
        this.inventory = inventory;
    }

    @Override
    public String getName() {
        return item.getItemInfo().name();
    }

    @Override
    public String getDescription() {
        return item.getItemInfo().name();
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void buy() {
        inventory.addItem(item);
    }
}
