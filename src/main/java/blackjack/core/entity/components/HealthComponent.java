package blackjack.core.entity.components;


public class HealthComponent {
    private int maxHp;
    private int currentHp;

    public HealthComponent(int maxHp) {
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage taken cannot be negative");
        }

        this.currentHp = Math.max(currentHp - damage, 0);
    }
    
    public void heal(int heal) {
        if (heal < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }

        this.currentHp = Math.min(heal + currentHp, maxHp);
    }

    public void resetHp() {
        this.currentHp = maxHp;
    }

    public boolean isAlive() {
        return (this.currentHp > 0);
    }

    public int getCurrentHp() { return currentHp; }
    public int getMaxHp() { return maxHp; }
}
