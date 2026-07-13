package blackjack.dtos.core.items;

public record ItemInfoDTO(String name, String description, int baseCost,
        boolean isManual, ItemTypeDTO itemType) {}
