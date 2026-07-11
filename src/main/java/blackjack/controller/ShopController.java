package blackjack.controller;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.DataSignal;
import blackjack.core.shop.Shop;

public class ShopController {
    private final DataSignal<Boolean> shopExited = new DataSignal<>();
    private final Shop shop;

    public ShopController(Shop shop) {
        this.shop = shop;
    }

    public void openShop() {
        // Implement shop opening logic here
    }

    public void shopExitedConnect(Consumer<Boolean> listener) {
        shopExited.connect(listener);
    }

    public List<String> getShopItemLines(Shop shop) {
        return shop.getItemsForSale().stream()
                .map(item -> item.getName() + " (" + item.getCost() + "g) - " + item.getDescription())
                .toList();
    }

    public int getPlayerGold() {
        return shop.getPlayer().getGold();
    }

    public boolean buyShopItem(Shop shop, int index) {
        return shop.buy(index, shop.getPlayer());
    }

    public boolean playerHasPurchasedCards(){
        return shop.getPlayer().hasPurchasedCards();
    }
}
