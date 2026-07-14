package blackjack.dtos.core.items;

import blackjack.core.inventory.ItemType;

public record ItemInfoDTO(
        String name,
        String description,
        String actionText,
        int baseCost,
        boolean isManual,
        ItemType itemType
    ) {}
