package blackjack.core.inventory;

import java.util.function.Consumer;

import blackjack.core.battle.BattleContextDTO;

public interface Item {
    ItemInfo getItemInfo();
    boolean isManual();
    boolean isConsumable();
    void trigger(BattleContextDTO ctx);
    Item copy();

    // Signal handling

    void triggeredConnect(Consumer<Item> consumer);
    default void outOfUsesConnect(Consumer<Item> consumer) {}
}
