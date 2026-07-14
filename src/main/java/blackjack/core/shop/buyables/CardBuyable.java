package blackjack.core.shop.buyables;

import blackjack.core.cards.Card;
import blackjack.core.cards.Deck;
import blackjack.core.inventory.ItemType;
import blackjack.core.shop.Buyable;

public class CardBuyable implements Buyable {
    private final Card card;
    private final int cost;
    private final Deck deck;

    public CardBuyable(Card card, int cost, Deck deck) {
        this.card = card;
        this.cost = cost;
        this.deck = deck;
    }

    @Override
    public String getName() {
        return card.toString();
    }

    @Override
    public String getDescription() {
        return "Adds this card to your deck";
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void buy() {
        deck.addCard(card);
    }

    @Override
    public ItemType getItemType() {
        return ItemType.PASSIVE;
    }
}