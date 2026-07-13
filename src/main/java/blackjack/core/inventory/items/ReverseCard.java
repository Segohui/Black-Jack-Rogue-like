package blackjack.core.inventory.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.dtos.core.items.ItemTypeDTO;
import blackjack.core.cards.Card;
import blackjack.core.entity.Entity;
import blackjack.core.inventory.Item;
import blackjack.exceptions.DeadItemException;

public class ReverseCard implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();
    private final DataSignal<Item> outOfUses = new DataSignal<>();

    private int uses = 1;

    @Override
    public ItemInfoDTO getItemInfo() {
        return new ItemInfoDTO(
            "Reverse Card",
            "When activated, change hands with your opponent. (%d use(s))".formatted(uses),
            7,
            isManual()
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
        Entity player = ctx.player();
        Entity enemy = ctx.enemy();

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
    public ItemTypeDTO getType(){
        return ItemTypeDTO.CONSUMABLE;
    }
}