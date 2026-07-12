package blackjack.core.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.core.signal.DataSignal;
import blackjack.core.battle.BattleContextDTO;
import blackjack.core.inventory.items.XRay;

public class Inventory {
    private final List<Item> items = new ArrayList<>();
    private int goldAmount = 0;
    private final DataSignal<String> itemPeeked = new DataSignal<>();

    public void addItem(Item item) {
        items.add(item);
        item.outOfUsesConnect(this::discardItem);

        if (item instanceof XRay xray) {
            xray.peekedConnect(itemPeeked::emit);
        }
    }

    public void discardItem(Item item) {
        items.remove(item);
    }

    public List<ItemInfo> getItemInfos() {
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

    public void itemPeekedConnect(Consumer<String> consumer) {
        itemPeeked.connect(consumer);
    }

    public void clearItemPeekedConnections() {
        itemPeeked.clearConnections();
    }
}
