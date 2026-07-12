package blackjack.controller;

import java.util.List;

import blackjack.core.inventory.Inventory;
import blackjack.core.shop.Shop;
import blackjack.core.signal.EmptySignal;
import blackjack.dtos.core.items.ShopItemDTO;

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

    public List<ShopItemDTO> getShopItems() {
        return shop.getItemsForSale().stream()
                .map(buyable -> new ShopItemDTO(
                        buyable.getName(), 
                        buyable.getItemType(), 
                        buyable.getDescription(), 
                        buyable.getCost()
                ))
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