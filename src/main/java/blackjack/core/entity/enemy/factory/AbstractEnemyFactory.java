package blackjack.core.entity.enemy.factory;

import blackjack.core.entity.enemy.blueprints.EnemyBlueprint;
import blackjack.dtos.entity.AIRecordDTO;

public interface AbstractEnemyFactory {
    AIRecordDTO generateRandomEnemy(float difficultyMultiplier);
    AIRecordDTO generateSpecificEnemy(EnemyBlueprint blueprint, float difficultyMultiplier);
}