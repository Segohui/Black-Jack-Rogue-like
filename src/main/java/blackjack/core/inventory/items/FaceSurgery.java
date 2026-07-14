package blackjack.core.inventory.items;

import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.core.entity.Entity;
import blackjack.core.entity.modifiers.MultDamageModifier;
import blackjack.core.inventory.Item;
import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.dtos.core.items.ItemTypeDTO;

public class FaceSurgery implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();

    @Override
    public ItemInfoDTO getItemInfo() {
        return new ItemInfoDTO(
            "Face Surgery",
            "Scored face cards deal twice as much damage.",
            6,
            isManual(),
            getType()
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
    public ItemTypeDTO getType() {
        return ItemTypeDTO.PASSIVE;
    }
}
