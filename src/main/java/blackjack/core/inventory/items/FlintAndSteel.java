package blackjack.core.inventory.items;

import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.core.cards.Deck;
import blackjack.core.entity.Entity;
import blackjack.core.inventory.Item;
import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.dtos.core.items.ItemTypeDTO;
import blackjack.exceptions.DeadItemException;

public class FlintAndSteel implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();
    private final DataSignal<Item> outOfUses = new DataSignal<>();

    private int uses = 3;

    @Override
    public ItemInfoDTO getItemInfo() {
        return new ItemInfoDTO(
            "Flint and STEEEL",
            "When activated, permanently burns the last card in your hand. (%d use(s))".formatted(uses),
            4,
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
        Deck deck = ctx.playerDeck();
        Card card = player.discardLastCardInHand();
        deck.burnCard(card);
        uses--;
        if (uses <= 0) {
            outOfUses.emit(this);
        }
    }

    @Override
    public Item copy() {
        return new FlintAndSteel();
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
    public ItemTypeDTO getType() {
        return ItemTypeDTO.CONSUMABLE;
    }
}
