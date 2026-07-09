package blackjack.core.shop;

import java.util.List;

import blackjack.entity.Entity;

public class Shop {
    private final List<ShopItem> itemsForSale;

    public Shop(List<ShopItem> itemsForSale) {
        this.itemsForSale = itemsForSale;
    }

    public List<ShopItem> getItemsForSale() {
        return itemsForSale;
    }

    public boolean buy(int index, Entity player) {
        if (index < 0 || index >= itemsForSale.size()) {
            return false;
        }

        ShopItem item = itemsForSale.get(index);
        if (!player.canAfford(item.getCost())) {
            return false;
        }

        player.spend(item.getCost());
        item.apply(player);
        itemsForSale.remove(index);
        return true;
    }
}