package blackjack.core.inventory.items;

import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.core.entity.capabilities.IRoundParticipant;
import blackjack.core.inventory.Item;
import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.dtos.core.items.ItemTypeDTO;
import blackjack.exceptions.DeadItemException;

public class XRay implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();
    private final DataSignal<Item> outOfUses = new DataSignal<>();
    private final DataSignal<String> peeked = new DataSignal<>();

    private int uses = 3;

    @Override
    public ItemInfoDTO getItemInfo() {
        return new ItemInfoDTO(
            "X-Ray",
            "When activated, peek at the next card in your stack. (%d use(s))".formatted(uses),
            5,
            isManual(),
            getType()
        );
    }

    @Override
    public boolean isManual() {
        return true;
    }

    @Override
    public boolean isConsumable() {
        return true;
    }

    @Override
    public void trigger(BattleContextDTO ctx) {

        if (uses <= 0) {
            throw new DeadItemException("Tried triggering an item that should no longer exist");
        }

        IRoundParticipant player = ctx.player();
        Card card = player.peekNextCard();
        peeked.emit(card.toString());

        uses--;
        triggered.emit(this);
        
        if (uses <= 0) {
            outOfUses.emit(this);
        }
    }

    @Override
    public Item copy() {
        return new XRay();
    }

    @Override
    public void triggeredConnect(Consumer<Item> consumer) {
        triggered.connect(consumer);
    }

    @Override
    public void outOfUsesConnect(Consumer<Item> consumer) {
        outOfUses.connect(consumer);
    }

    public void peekedConnect(Consumer<String> consumer) {
        peeked.connect(consumer);   
    }

    @Override 
    public ItemTypeDTO getType(){
        return ItemTypeDTO.CONSUMABLE;
    }
}