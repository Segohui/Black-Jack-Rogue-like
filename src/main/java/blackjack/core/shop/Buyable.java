package blackjack.core.shop;

import blackjack.dtos.core.items.ItemTypeDTO;

public interface Buyable {
    String getName();
    String getDescription();
    int getCost();
    void buy();
    ItemTypeDTO getItemType();
}