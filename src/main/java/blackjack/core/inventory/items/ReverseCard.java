package blackjack.core.inventory.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.core.cards.Card;
import blackjack.core.entity.capabilities.IRoundParticipant;
import blackjack.core.inventory.Item;
import blackjack.core.inventory.ItemType;
import blackjack.exceptions.DeadItemException;

/**
 * Consumable item that swaps the player's and opponent's hands.
 */
public class ReverseCard implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();
    private final DataSignal<Item> outOfUses = new DataSignal<>();

    private int uses = 1;

    @Override
    public ItemInfoDTO getItemInfo() {
        return new ItemInfoDTO(
            "Reverse Card",
            "When activated, change hands with your opponent. (%d use(s))".formatted(uses),
            "Reverse card! You switched hands with your opponent.",
            7,
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
        IRoundParticipant enemy = ctx.enemy();

        List<Card> enemyCards = enemy.setHand(new ArrayList<>());
        List<Card> playerCards = player.setHand(enemyCards);
        enemy.setHand(playerCards);

        uses--;
        triggered.emit(this);
        if (uses <= 0) {
            outOfUses.emit(this);
        }
    }

    @Override
    public Item copy() {
        return new ReverseCard();
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