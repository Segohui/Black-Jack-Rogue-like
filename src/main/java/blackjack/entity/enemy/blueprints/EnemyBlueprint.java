package blackjack.entity.enemy.blueprints;

import blackjack.entity.enemy.behaviors.Behavior;

public interface EnemyBlueprint {
    String getName();
    int getBaseHp();
    Behavior getBehavior();
    int getGoldReward();
}