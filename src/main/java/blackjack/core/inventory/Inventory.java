package blackjack.core.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;

/**
 * Represents the player's inventory, including items and gold.
 *
 * <p>This class presents a rich inventory model with behavior for adding
 * items, triggering effects, and enforcing gold constraints.</p>
 */
public class Inventory {
    private final DataSignal<ItemInfoDTO> itemTriggered = new DataSignal<>();

    private final List<Item> items = new ArrayList<>();
    private int goldAmount = 0;

    /**
     * Adds an item to inventory and wires its event signals.
     *
     * @param item item to add
     */
    public void addItem(Item item) {
        items.add(item);
        item.triggeredConnect(t -> itemTriggered.emit(t.getItemInfo()));
        item.outOfUsesConnect(this::discardItem);
    }

    /**
     * Removes an item from inventory.
     *
     * @param item item to discard
     */
    public void discardItem(Item item) {
        items.remove(item);
    }

    /**
     * Returns item view models for the current inventory.
     *
     * @return list of item info DTOs
     */
    public List<ItemInfoDTO> getItemInfos() {
        return items.stream()
                .map(Item::getItemInfo)
                .toList();
    }

    /**
     * Triggers the manual use of the item at the given index.
     *
     * @param index index of the item to trigger
     * @param ctx current battle context
     */
    public void triggerItem(int index, BattleContextDTO ctx) {
        items.get(index).trigger(ctx);
    }

    /**
     * Automatically triggers all non-manual items in inventory.
     *
     * @param ctx current battle context
     */
    public void triggerItemsAuto(BattleContextDTO ctx) {
        for (Item item: items) {
            if (!item.isManual()) {
                item.trigger(ctx);
            }
        }
    }

    /**
     * Checks whether the inventory contains any items.
     *
     * @return true when there are items in inventory
     */
    public boolean hasItems() {
        return !items.isEmpty();
    }

    /**
     * Checks whether the player can afford a cost.
     *
     * @param cost gold cost to compare
     * @return true when available gold is sufficient
     */
    public boolean canAfford(int cost) {
        return goldAmount >= cost;
    }

    /**
     * Adds gold to the player inventory.
     *
     * @param amount amount of gold to add
     * @throws IllegalArgumentException when amount is negative
     */
    public void addGold(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }
        goldAmount += amount;
    }

    /**
     * Spends gold from the inventory.
     *
     * @param cost cost to spend
     * @throws IllegalArgumentException when cost is negative
     * @throws IllegalStateException when there is insufficient gold
     */
    public void spendGold(int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

        if (!canAfford(cost)) {
            throw new IllegalStateException("Not enough gold to spend " + cost);
        }

        goldAmount -= cost;
    }

    /**
     * Returns the current gold amount.
     *
     * @return current gold amount
     */
    public int getGoldAmount() {
        return goldAmount;
    }

    /**
     * Registers a listener for item-triggered events.
     *
     * @param consumer callback invoked when an item is triggered
     */
    public void itemTriggeredConnect(Consumer<ItemInfoDTO> consumer) {
        itemTriggered.connect(consumer);
    }

    /**
     * Clears all listeners connected to item-triggered events.
     */
    public void clearItemTriggeredConnections() {
        itemTriggered.clearConnections();
    }
}
