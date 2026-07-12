package blackjack.entity.enemy.factory;

import blackjack.dtos.entity.AIRecordDTO;
import blackjack.entity.enemy.blueprints.EnemyBlueprint;

public interface AbstractEnemyFactory {
    AIRecordDTO generateRandomEnemy(float difficultyMultiplier);
    AIRecordDTO generateSpecificEnemy(EnemyBlueprint blueprint, float difficultyMultiplier);
}