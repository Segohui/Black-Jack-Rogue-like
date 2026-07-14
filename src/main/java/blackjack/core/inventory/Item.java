package blackjack.core.inventory;

import java.util.function.Consumer;

import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;

/**
 * Represents an inventory item that can be triggered during battle.
 *
 * <p>Items expose a rich behavior model and event hooks for the inventory
 * system to consume. This interface is designed to keep item usage decoupled
 * from the rest of the game logic.</p>
 */
public interface Item {
    /**
     * Returns the view representation of this item.
     *
     * @return item info DTO
     */
    ItemInfoDTO getItemInfo();

    /**
     * Returns whether the item must be manually activated by the player.
     *
     * @return true when manual activation is required
     */
    boolean isManual();

    /**
     * Returns whether the item is consumable and can be used only once.
     *
     * @return true when the item is consumable
     */
    boolean isConsumable();

    /**
     * Executes the item effect for the current battle context.
     *
     * @param ctx current battle context
     */
    void trigger(BattleContextDTO ctx);

    /**
     * Creates a copy of this item.
     *
     * @return copy of the item
     */
    Item copy();

    /**
     * Returns the item type classification.
     *
     * @return item type
     */
    ItemType getType();

    // Signal handling

    /**
     * Registers a listener for when the item triggers.
     *
     * @param consumer callback invoked when this item triggers
     */
    void triggeredConnect(Consumer<Item> consumer);

    /**
     * Registers a listener for when the item has no remaining uses.
     *
     * @param consumer callback invoked when the item expires
     */
    default void outOfUsesConnect(Consumer<Item> consumer) {}
}
