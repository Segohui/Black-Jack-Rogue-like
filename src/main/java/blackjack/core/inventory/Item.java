package blackjack.core.inventory;

import java.util.function.Consumer;

import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;

public interface Item {
    ItemInfoDTO getItemInfo();
    boolean isManual();
    boolean isConsumable();
    void trigger(BattleContextDTO ctx);
    Item copy();
    ItemType getType();

    // Signal handling

    void triggeredConnect(Consumer<Item> consumer);
    default void outOfUsesConnect(Consumer<Item> consumer) {}
}
