package blackjack.core.inventory;

/**
 * Categorizes inventory items by how they are used in combat.
 */
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