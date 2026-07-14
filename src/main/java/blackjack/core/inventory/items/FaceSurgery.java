package blackjack.core.inventory.items;

import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.core.entity.capabilities.IRoundParticipant;
import blackjack.core.entity.modifiers.MultDamageModifier;
import blackjack.core.inventory.Item;
import blackjack.core.inventory.ItemType;
import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;

public class FaceSurgery implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();

    private final int MULT = 2;

    private Card affectedCard;

    @Override
    public ItemInfoDTO getItemInfo() {
        return new ItemInfoDTO(
            "Face Surgery",
            "Scored face cards deal twice as much damage.",
            "Face Surgery: %dx damage to %s".formatted(MULT, affectedCard),
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
        IRoundParticipant player = ctx.player();
        for (Card card : player.getCards()) {
            if (card.isFaceCard()) {
                player.addDamageCardModifier(card, new MultDamageModifier(2));
                affectedCard = card;
                triggered.emit(this);
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
