package blackjack.core.inventory.items;

import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.core.cards.enums.Suit;
import blackjack.core.entity.capabilities.IRoundParticipant;
import blackjack.core.entity.modifiers.AdditionDamageModifier;
import blackjack.core.inventory.Item;
import blackjack.core.inventory.ItemType;
import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.BattleContextDTO;
import blackjack.dtos.core.items.ItemInfoDTO;

/**
 * Passive item that adds extra damage to cards of the diamond suit.
 */
public class FortunePickaxe implements Item {
    private final DataSignal<Item> triggered = new DataSignal<>();

    private final int EXTRA_DAMAGE = 9;

    private Card affectedCard;

    @Override
    public ItemInfoDTO getItemInfo() {
        return new ItemInfoDTO(
            "Fortune Pickaxe",
            "Scored cards of Diamonds add +%d damage.".formatted(EXTRA_DAMAGE),
            "Fortune Pickaxe: +%d damage to %s".formatted(EXTRA_DAMAGE, affectedCard),
            6,
            isManual(),
            ItemType.PASSIVE
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
            if (card.getSuit() == Suit.DIAMONDS) {
                player.addDamageCardModifier(card, new AdditionDamageModifier(EXTRA_DAMAGE));
                affectedCard = card;
                triggered.emit(this);
            }
        }
    }

    @Override
    public Item copy() {
        return new FortunePickaxe();
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
