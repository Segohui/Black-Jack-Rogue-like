package blackjack.dtos.core.items;

public enum ItemTypeDTO {
    CONSUMABLE("Consumível"),
    ACTIVE("Ativável"),
    PASSIVE("Passivo");

    private final String displayName;

    ItemTypeDTO(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}