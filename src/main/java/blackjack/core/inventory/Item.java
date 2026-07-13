package blackjack.core.inventory;

import java.util.function.Consumer;

import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.dtos.core.items.ItemTypeDTO;

public interface Item {
    ItemInfoDTO getItemInfo();
    boolean isManual();
    boolean isConsumable();
    void trigger(BattleContextDTO ctx);
    Item copy();
    ItemTypeDTO getType();

    // Signal handling

    void triggeredConnect(Consumer<Item> consumer);
    default void outOfUsesConnect(Consumer<Item> consumer) {}
}
