package blackjack.core.entity.enemy.blueprints;

import java.util.function.Supplier;

import blackjack.core.entity.enemy.behaviors.Behavior;
import blackjack.core.entity.enemy.behaviors.PercentageBehavior;

/**
 * Defines the enemy blueprints used by the Isaac-themed encounter set.
 */
public enum IsaacEnemies implements EnemyBlueprint {  
    DOPLE("Dople", 20, 5, () -> new PercentageBehavior(1.0)),
    TERATOMA("Teratoma", 40, 6, () -> new PercentageBehavior(0.3)),
    KRAMPUS("Krampus", 60, 7, () -> new PercentageBehavior(3.0));

    private final String enemyName;
    private final int baseHp;
    private final int goldReward;
    private final Supplier<Behavior> behaviorSupplier;

    IsaacEnemies(String name, int baseHp, int goldReward, Supplier<Behavior> behaviorSupplier) {
        this.enemyName = name;
        this.baseHp = baseHp;
        this.goldReward = goldReward;
        this.behaviorSupplier = behaviorSupplier;
    }

    @Override
    public String getName() { return enemyName; }
    
    @Override
    public int getBaseHp() { return baseHp; }
    
    @Override
    public Behavior getBehavior() { return behaviorSupplier.get(); }

    @Override
    public int getGoldReward() { return goldReward; }
}