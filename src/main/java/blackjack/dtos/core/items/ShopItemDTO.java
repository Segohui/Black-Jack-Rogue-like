package blackjack.dtos.core.items;

import blackjack.core.inventory.ItemType;

public record ShopItemDTO(
        String name,
        ItemType itemType,
        String description,
        int price
    ) {}