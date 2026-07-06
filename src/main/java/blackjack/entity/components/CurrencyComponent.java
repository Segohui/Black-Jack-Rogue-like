package blackjack.entity.components;

public class CurrencyComponent {
    private int gold;

    public CurrencyComponent(int startingGold) {
        if (startingGold < 0) {
            throw new IllegalArgumentException("Starting gold cannot be negative");
        }

        this.gold = startingGold;
    }

    public boolean canAfford(int cost) {
        return gold >= cost;
    }

    public void spend(int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

        if (!canAfford(cost)) {
            throw new IllegalStateException("Not enough gold to spend " + cost);
        }

        gold -= cost;
    }

    public void add(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }

        gold += amount;
    }

    public int getGold() {
        return gold;
    }
}