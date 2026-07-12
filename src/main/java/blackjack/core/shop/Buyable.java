package blackjack.core.shop;

import blackjack.dtos.core.items.ItemType;

public interface Buyable {
    String getName();
    String getDescription();
    int getCost();
    void buy();
    ItemType getItemType();
}