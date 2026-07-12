package blackjack.core.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.iterators.ArrayListIterator;

import blackjack.core.inventory.items.FlintAndSteel;

public class ItemRegistry {
    private final List<Item> baseItems;

    private Iterator<Item> iterator;

    public ItemRegistry() {
        this.baseItems = new ArrayList<>();
        shuffleBaseItems();
        this.iterator = new ArrayListIterator<>(baseItems);

        populateBaseItems();
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
            iterator = new ArrayListIterator<>(baseItems);
        }
        Item baseItem = iterator.next();
        return baseItem.copy();
    }
}
