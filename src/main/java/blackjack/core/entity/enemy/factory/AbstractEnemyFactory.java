package blackjack.core.entity.enemy.factory;

import blackjack.core.entity.enemy.blueprints.EnemyBlueprint;
import blackjack.dtos.entity.AIRecordDTO;

/**
 * Defines how enemy AI records are generated for battle encounters.
 */
public interface AbstractEnemyFactory {
    AIRecordDTO generateRandomEnemy(float difficultyMultiplier);
    AIRecordDTO generateSpecificEnemy(EnemyBlueprint blueprint, float difficultyMultiplier);
}