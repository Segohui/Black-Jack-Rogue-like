package blackjack.entity.components;


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

        this.currentHp -= damage;
    }
    
    public void heal(int heal) {
        if (heal < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }

        this.currentHp = Math.max(heal + currentHp, maxHp);
    }

    public void resetHp() {
        this.currentHp = maxHp;
    }

    public boolean isAlive() {
        if (this.currentHp > 0) {
            return true;
        }

        return false;
    }

    public int getCurrentHp() { return currentHp; }
    public int getMaxHp() { return maxHp; }
}
