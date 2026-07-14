package blackjack.controller;

import java.util.List;

import blackjack.core.inventory.Inventory;
import blackjack.core.shop.Shop;
import blackjack.core.signal.EmptySignal;
import blackjack.dtos.core.items.ShopItemDTO;
import blackjack.exceptions.InsufficientGoldException;

/**
 * Controller that exposes shop operations and state to the terminal view layer.
 *
 * <p>This class manages shop lifecycle events and the interaction between the
 * player's inventory and the shop domain.</p>
 */
public class ShopController {
    private final EmptySignal shopEntered = new EmptySignal();
    private final EmptySignal shopExited = new EmptySignal();

    private final Shop shop;
    private final Inventory playerInventory;

    /**
     * Creates a shop controller that coordinates shop and inventory interactions.
     *
     * @param shop shop domain used to populate and purchase items
     * @param playerInventory shared player inventory for gold and items
     */
    public ShopController(Shop shop, Inventory playerInventory) {
        this.shop = shop;
        this.playerInventory = playerInventory;
    }

    /**
     * Enters the shop, populating it with available buyables.
     */
    public void enterShop() {
        shop.populateWithItems();
        shopEntered.emit();
    }

    /**
     * Exits the shop and clears the current shop items.
     */
    public void exitShop() {
        shop.clearItems();
        shopExited.emit();
    }

    /**
     * Returns the shop items currently available for purchase.
     *
     * @return list of shop item DTOs
     */
    public List<ShopItemDTO> getShopItems() {
        return shop.getShopItems();
    }

    /**
     * Returns the player current gold amount.
     *
     * @return current gold amount
     */
    public int getPlayerGold() {
        return playerInventory.getGoldAmount();
    }

    /**
     * Attempts to purchase the selected shop item.
     *
     * @param index index of the item to purchase
     * @return true when the purchase succeeds
     * @throws InsufficientGoldException when the player lacks enough gold
     */
    public boolean buyShopItem(int index) throws InsufficientGoldException{
        return shop.buy(index);
    }

    /**
     * Returns whether the player has any items in inventory.
     *
     * @return true when the player has at least one item
     */
    public boolean playerHasItems() {
        return playerInventory.hasItems();
    }

    /**
     * Connects a listener to the shop-entered event.
     *
     * @param runnable callback invoked when the player enters the shop
     */
    public void shopEnteredConnect(Runnable runnable) {
        shopEntered.connect(runnable);
    }

    /**
     * Connects a listener to the shop-exited event.
     *
     * @param runnable callback invoked when the player exits the shop
     */
    public void shopExitedConnect(Runnable runnable) {
        shopExited.connect(runnable);
    }
}