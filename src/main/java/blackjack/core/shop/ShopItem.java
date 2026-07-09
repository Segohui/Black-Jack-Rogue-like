package blackjack.core.shop;

import blackjack.entity.Entity;

public interface ShopItem {
    String getName();
    String getDescription();
    int getCost();
    void apply(Entity player);
}