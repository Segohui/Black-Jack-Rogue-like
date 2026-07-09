package blackjack.core.shop.items;

import blackjack.core.cards.Card;
import blackjack.core.shop.ShopItem;
import blackjack.entity.Entity;

public class CardItem implements ShopItem {
    private final Card card;
    private final int cost;

    public CardItem(Card card, int cost) {
        this.card = card;
        this.cost = cost;
    }

    @Override
    public String getName() {
        return card.toString();
    }

    @Override
    public String getDescription() {
        return "Adds this card to your available cards";
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void apply(Entity player) {
        player.addPurchasedCard(card);
    }
}