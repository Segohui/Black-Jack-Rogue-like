package blackjack.entity.enemy.factory;

import blackjack.entity.enemy.AIRecord;
import blackjack.entity.enemy.blueprints.EnemyBlueprint;

public interface AbstractEnemyFactory {
    AIRecord generateRandomEnemy(float difficultyMultiplier);
    AIRecord generateSpecificEnemy(EnemyBlueprint blueprint, float difficultyMultiplier);
}