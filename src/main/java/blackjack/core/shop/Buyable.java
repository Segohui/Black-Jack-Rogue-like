package blackjack.core.shop;

import blackjack.core.inventory.ItemType;

public interface Buyable {
    String getName();
    String getDescription();
    int getCost();
    void buy();
    ItemType getItemType();
}