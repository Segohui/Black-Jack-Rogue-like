package blackjack.dtos.core.items;

import blackjack.core.inventory.ItemType;

/**
 * Carries the metadata used to display an item in the UI.
 *
 * @param name item display name
 * @param description item description
 * @param actionText text shown when the item is used
 * @param baseCost base purchase cost
 * @param isManual whether activation is manual
 * @param itemType item category
 */
public record ItemInfoDTO(
        String name,
        String description,
        String actionText,
        int baseCost,
        boolean isManual,
        ItemType itemType
    ) {}
