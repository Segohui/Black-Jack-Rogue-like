package blackjack.core.entity.enemy.blueprints;

import blackjack.core.entity.enemy.behaviors.Behavior;

public interface EnemyBlueprint {
    String getName();
    int getBaseHp();
    Behavior getBehavior();
    int getGoldReward();
}