package blackjack.entity.enemy.blueprints;

import blackjack.entity.enemy.behaviors.Behavior;
import blackjack.entity.enemy.behaviors.ThresholdBehaviour;

public enum MineEnemies implements EnemyBlueprint {  
    VILLAGER("Villager", 20, 5, new ThresholdBehaviour(6)),
    ZOMBIE("Zumbi", 40, 6, new ThresholdBehaviour(4)),
    HEROBRINE("Herobrine", 60, 7, new ThresholdBehaviour(3));

    private final String enemyName;
    private final int baseHp;
    private final int goldReward;
    private final Behavior behavior;

    MineEnemies(String name, int baseHp, int goldReward, Behavior behavior) {
        this.enemyName = name;
        this.baseHp = baseHp;
        this.goldReward = goldReward;
        this.behavior = behavior;
    }

    @Override
    public String getName() { return enemyName; }
    
    @Override
    public int getBaseHp() { return baseHp; }
    
    @Override
    public Behavior getBehavior() { return behavior; }

    @Override
    public int getGoldReward() { return goldReward; }
}