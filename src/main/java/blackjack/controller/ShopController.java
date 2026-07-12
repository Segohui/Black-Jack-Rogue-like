package blackjack.controller;

import java.util.List;

import blackjack.core.EmptySignal;
import blackjack.core.inventory.Inventory;
import blackjack.core.shop.Shop;

public class ShopController {
    private final EmptySignal shopEntered = new EmptySignal();
    private final EmptySignal shopExited = new EmptySignal();

    private final Shop shop;
    private final Inventory playerInventory;

    public ShopController(Shop shop, Inventory playerInventory) {
        this.shop = shop;
        this.playerInventory = playerInventory;
    }

    public void enterShop() {
        shop.populateWithItems();
        shopEntered.emit();
    }

    public void exitShop() {
        shop.clearItems();
        shopExited.emit();
    }

    public List<String> getShopItemLines() {
        return shop.getItemsForSale().stream()
                .map(buyable -> "%s (%dg) - %s".formatted(
                        buyable.getName(), buyable.getCost(), buyable.getDescription()))
                .toList();
    }

    public int getPlayerGold() {
        return playerInventory.getGoldAmount();
    }

    public boolean buyShopItem(int index) {
        return shop.buy(index);
    }

    public boolean playerHasItems() {
        return playerInventory.hasItems();
    }

    public void shopEnteredConnect(Runnable runnable) {
        shopEntered.connect(runnable);
    }

    public void shopExitedConnect(Runnable runnable) {
        shopExited.connect(runnable);
    }
}
