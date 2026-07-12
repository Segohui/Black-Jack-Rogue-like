package blackjack.core.inventory.items;

import java.util.function.Consumer;

import blackjack.core.DataSignal;
import blackjack.core.battle.BattleContextDTO;
import blackjack.core.cards.Card;
import blackjack.core.cards.Deck;
import blackjack.core.inventory.Item;
import blackjack.core.inventory.ItemInfo;
import blackjack.entity.Entity;
import blackjack.exceptions.DeadItemException;

public class FlintAndSteel implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();
    private final DataSignal<Item> outOfUses = new DataSignal<>();

    private int uses = 3;

    @Override
    public ItemInfo getItemInfo() {
        return new ItemInfo(
            "Flint and STEEEL",
            "When activated, permanently burns the last card in your hand."
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
}
