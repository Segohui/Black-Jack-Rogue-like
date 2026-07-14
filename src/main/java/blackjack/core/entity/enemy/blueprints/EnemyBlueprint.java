package blackjack.core.entity.enemy.blueprints;

import blackjack.core.entity.enemy.behaviors.Behavior;

/**
 * Describes the shared data required to build a combat enemy.
 */
public interface EnemyBlueprint {
    String getName();
    int getBaseHp();
    Behavior getBehavior();
    int getGoldReward();
}