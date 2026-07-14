package blackjack.core.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import blackjack.core.inventory.items.BallAndChain;
import blackjack.core.inventory.items.Cannon;
import blackjack.core.inventory.items.FaceSurgery;
import blackjack.core.inventory.items.FlintAndSteel;
import blackjack.core.inventory.items.FortunePickaxe;
import blackjack.core.inventory.items.ReverseCard;
import blackjack.core.inventory.items.XRay;

/**
 * Registry of item prototypes used to generate shop items.
 *
 * <p>This class provides a simple domain registry that can be extended with
 * additional item types without impacting the shop or inventory logic.</p>
 */
public class ItemRegistry {
    private final List<Item> baseItems;

    private Iterator<Item> iterator;

    /**
     * Initializes the item registry and shuffles the available prototypes.
     */
    public ItemRegistry() {
        this.baseItems = new ArrayList<>();
        populateBaseItems();
        shuffleBaseItems();
        this.iterator = baseItems.iterator();
    }

    /**
     * Populates the registry with the supported base item types.
     */
    private void populateBaseItems() {
        baseItems.add(new FlintAndSteel());
        baseItems.add(new FaceSurgery());
        baseItems.add(new XRay());
        baseItems.add(new ReverseCard());
        baseItems.add(new BallAndChain());
        baseItems.add(new Cannon());
        baseItems.add(new FortunePickaxe());
        // add new items here
    }

    /**
     * Randomizes the order of base items.
     */
    private void shuffleBaseItems() {
        Collections.shuffle(baseItems);
    }

    /**
     * Creates the next item from the registry, copying a prototype.
     *
     * @return next item instance
     */
    public Item createNextItem() {
        if (!iterator.hasNext()) {
            shuffleBaseItems();
            iterator = baseItems.iterator();
        }
        Item baseItem = iterator.next();
        return baseItem.copy();
    }
}
