package blackjack.core.entity.capabilities;

public interface IDamageable {
    int getCurrentHp();
    void takeDamage(int damage);
    void heal(int amount);
    boolean isAlive();
}
