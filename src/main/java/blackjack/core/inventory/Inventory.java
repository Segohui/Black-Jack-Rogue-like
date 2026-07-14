package blackjack.core.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;

public class Inventory {
    private final DataSignal<ItemInfoDTO> itemTriggered = new DataSignal<>();

    private final List<Item> items = new ArrayList<>();
    private int goldAmount = 0;

    public void addItem(Item item) {
        items.add(item);
        item.triggeredConnect(t -> itemTriggered.emit(t.getItemInfo()));
        item.outOfUsesConnect(this::discardItem);
    }

    public void discardItem(Item item) {
        items.remove(item);
    }

    public List<ItemInfoDTO> getItemInfos() {
        return items.stream()
                .map(Item::getItemInfo)
                .toList();
    }

    public void triggerItem(int index, BattleContextDTO ctx) {
        items.get(index).trigger(ctx);
    }

    public void triggerItemsAuto(BattleContextDTO ctx) {
        for (Item item: items) {
            if (!item.isManual()) {
                item.trigger(ctx);
            }
        }
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public boolean canAfford(int cost) {
        return goldAmount >= cost;
    }

    public void addGold(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }
        goldAmount += amount;
    }

    public void spendGold(int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

        if (!canAfford(cost)) {
            throw new IllegalStateException("Not enough gold to spend " + cost);
        }

        goldAmount -= cost;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void itemTriggeredConnect(Consumer<ItemInfoDTO> consumer) {
        itemTriggered.connect(consumer);
    }

    public void clearItemTriggeredConnections() {
        itemTriggered.clearConnections();
    }
}
