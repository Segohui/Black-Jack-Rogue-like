package blackjack.dtos.core.items;

import blackjack.core.inventory.ItemType;

/**
 * Data transfer object for items presented in the in-game shop.
 *
 * <p>This record carries the minimal presentation details required by the
 * shop UI: a display `name`, the `itemType` category, a short
 * `description`, and the `price` in gold. It is intentionally immutable and
 * lightweight so it can be produced on-demand by the domain layer.</p>
 *
 * @param name display name of the shop item
 * @param itemType category of the item (see {@link ItemType})
 * @param description brief description shown to the player
 * @param price purchase cost in gold
 */
public record ShopItemDTO(
        String name,
        ItemType itemType,
        String description,
        int price
    ) {}