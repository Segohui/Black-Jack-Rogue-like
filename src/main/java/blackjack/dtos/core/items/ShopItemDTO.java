package blackjack.dtos.core.items;

public record ShopItemDTO(
    String name,
    ItemTypeDTO itemType,
    String description,
    int price
) {}