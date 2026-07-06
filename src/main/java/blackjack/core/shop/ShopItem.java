package blackjack.core.shop;

import blackjack.entity.Player;

public interface ShopItem {
    String getName();
    String getDescription();
    int getCost();
    void apply(Player player);
}