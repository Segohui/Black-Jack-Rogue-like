package blackjack.core.inventory.items;

import java.util.function.Consumer;

import blackjack.core.battle.BattleContextDTO;
import blackjack.core.cards.Card;
import blackjack.core.inventory.Item;
import blackjack.core.inventory.ItemInfo;
import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.items.ItemType;
import blackjack.entity.Entity;
import blackjack.entity.modifiers.MultDamageModifier;

public class FaceSurgery implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();

    @Override
    public ItemInfo getItemInfo() {
        return new ItemInfo(
            "Face Surgery",
            "Scored face cards deal twice as much damage.",
            6,
            isManual()
        );
    }

    @Override
    public boolean isManual() {
        return false;
    }

    @Override
    public boolean isConsumable() {
        return false;
    }

    @Override
    public void trigger(BattleContextDTO ctx) {
        Entity player = ctx.player();
        for (Card card : player.getCards()) {
            if (card.isFaceCard()) {
                player.addDamageCardModifier(card, new MultDamageModifier(2));
            }
        }
    }

    @Override
    public Item copy() {
        return new FaceSurgery();
    }

    @Override
    public void triggeredConnect(Consumer<Item> consumer) {
        triggered.connect(consumer);
    }

    @Override
    public ItemType getType() {
        return ItemType.PASSIVE;
    }
}
