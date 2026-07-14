package blackjack.core.entity.capabilities;

/**
 * Defines the health and survivability contract for entities.
 */
public interface IDamageable {
    int getCurrentHp();
    void takeDamage(int damage);
    void heal(int amount);
    boolean isAlive();
}
