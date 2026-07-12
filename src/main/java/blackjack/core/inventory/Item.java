package blackjack.core.inventory;

import java.util.function.Consumer;

import blackjack.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemType;

public interface Item {
    ItemInfo getItemInfo();
    boolean isManual();
    boolean isConsumable();
    void trigger(BattleContextDTO ctx);
    Item copy();
    ItemType getType();

    // Signal handling

    void triggeredConnect(Consumer<Item> consumer);
    default void outOfUsesConnect(Consumer<Item> consumer) {}
}
