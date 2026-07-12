package blackjack.core.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import blackjack.core.inventory.items.FlintAndSteel;

public class ItemRegistry {
    private final List<Item> baseItems;

    private Iterator<Item> iterator;

    public ItemRegistry() {
        this.baseItems = new ArrayList<>();
        populateBaseItems();
        shuffleBaseItems();
        this.iterator = baseItems.iterator();
    }

    private void populateBaseItems() {
        baseItems.add(new FlintAndSteel());
        // add new items here
    }

    private void shuffleBaseItems() {
        Collections.shuffle(baseItems);
    }

    public Item createNextItem() {
        if (!iterator.hasNext()) {
            shuffleBaseItems();
            iterator = baseItems.iterator();
        }
        Item baseItem = iterator.next();
        return baseItem.copy();
    }
}
