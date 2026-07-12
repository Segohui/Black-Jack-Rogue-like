package blackjack.dtos.core.items;

public record ShopItemDTO(
    String name,
    ItemType itemType,
    String description,
    int price
) {}