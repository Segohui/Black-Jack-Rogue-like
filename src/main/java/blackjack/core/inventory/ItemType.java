package blackjack.core.inventory;

public enum ItemType {
    CONSUMABLE("Consumível"),
    ACTIVE("Ativável"),
    PASSIVE("Passivo");

    private final String displayName;

    ItemType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}