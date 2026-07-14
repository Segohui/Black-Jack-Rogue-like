package blackjack.core.shop.buyables;

import blackjack.core.inventory.Inventory;
import blackjack.core.inventory.Item;
import blackjack.core.inventory.ItemType;
import blackjack.core.shop.Buyable;

/**
 * Shop buyable that grants a new item to the player inventory.
 */
public class ItemBuyable implements Buyable {
    private final Item item;
    private final Inventory inventory;

    public ItemBuyable(Item item, Inventory inventory) {
        this.item = item;
        this.inventory = inventory;
    }

    @Override
    public String getName() {
        return item.getItemInfo().name();
    }

    @Override
    public String getDescription() {
        return item.getItemInfo().description();
    }

    @Override
    public int getCost() {
        return item.getItemInfo().baseCost();
    }

    @Override
    public void buy() {
        inventory.addItem(item);
    }

    @Override
    public ItemType getItemType() {
        return item.getType();
    }
}
