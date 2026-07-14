package blackjack.core.inventory.items;

import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.core.entity.capabilities.IRoundParticipant;
import blackjack.core.inventory.Item;
import blackjack.core.inventory.ItemType;
import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.exceptions.DeadItemException;

/**
 * Consumable item that reveals the next card in the player's draw stack.
 */
public class XRay implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();
    private final DataSignal<Item> outOfUses = new DataSignal<>();

    private int uses = 3;
    private Card peekedCard;

    @Override
    public ItemInfoDTO getItemInfo() {
        return new ItemInfoDTO(
            "X-Ray",
            "When activated, peek at the next card in your stack. (%d use(s))".formatted(uses),
            "X-ray activated! (%d use(s) left). Peeked card: %s".formatted(uses, peekedCard),
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
        peekedCard = player.peekNextCard();

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

    @Override 
    public ItemType getType(){
        return ItemType.CONSUMABLE;
    }
}