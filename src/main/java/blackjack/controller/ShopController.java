package blackjack.controller;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.DataSignal;
import blackjack.core.inventory.Inventory;
import blackjack.core.shop.Shop;

public class ShopController {
    private final DataSignal<Boolean> shopExited = new DataSignal<>();
    private final Shop shop;
    private final Inventory playerInventory;

    public ShopController(Shop shop, Inventory playerInventory) {
        this.shop = shop;
        this.playerInventory = playerInventory;
    }

    public void openShop() {
        // Implement shop opening logic here
    }

    public void shopExitedConnect(Consumer<Boolean> listener) {
        shopExited.connect(listener);
    }

    public List<String> getShopItemLines() {
        return shop.getItemsForSale().stream()
                .map(item -> item.getName() + " (" + item.getCost() + "g) - " + item.getDescription())
                .toList();
    }

    public int getPlayerGold() {
        return playerInventory.getGoldAmount();
    }

    public boolean buyShopItem(int index) {
        return shop.buy(index);
    }

    public boolean playerHasItems(){
        return playerInventory.hasItems();
    }
}
