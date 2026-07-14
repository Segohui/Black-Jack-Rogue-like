package blackjack.core.shop;

import blackjack.core.inventory.ItemType;

/**
 * Represents a shop item that can be purchased by the player.
 *
 * <p>Buyable items expose metadata and a purchase action, allowing the shop
 * and UI layers to remain decoupled from concrete item implementations.</p>
 */
public interface Buyable {
    /**
     * Returns the display name of the buyable.
     *
     * @return item name
     */
    String getName();

    /**
     * Returns a short description of the buyable.
     *
     * @return item description
     */
    String getDescription();

    /**
     * Returns the gold cost to purchase this item.
     *
     * @return purchase cost
     */
    int getCost();

    /**
     * Executes the purchase behavior for this item.
     */
    void buy();

    /**
     * Returns the type of item produced by this buyable.
     *
     * @return item type
     */
    ItemType getItemType();
}