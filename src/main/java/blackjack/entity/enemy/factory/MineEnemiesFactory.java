package blackjack.entity.enemy.factory;

import java.util.concurrent.ThreadLocalRandom;

import blackjack.entity.CombatEntity;
import blackjack.entity.Entity;
import blackjack.entity.enemy.AIRecord;
import blackjack.entity.enemy.blueprints.MineEnemies;
import blackjack.entity.enemy.blueprints.EnemyBlueprint;

public class MineEnemiesFactory implements AbstractEnemyFactory {

    @Override
    public AIRecord generateRandomEnemy(float difficultyMultiplier) {
        MineEnemies[] enemyPool = MineEnemies.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(enemyPool.length);
        EnemyBlueprint blueprint = enemyPool[randomIndex];

        return buildEnemy(blueprint, difficultyMultiplier);
    }

    @Override
    public AIRecord generateSpecificEnemy(EnemyBlueprint blueprint, float difficultyMultiplier) {
        return buildEnemy(blueprint, difficultyMultiplier);
    }

    private AIRecord buildEnemy(EnemyBlueprint blueprint, float multiplier) {
        int finalHp = (int) (blueprint.getBaseHp() * multiplier);
        
        Entity enemyEntity = new CombatEntity(blueprint.getName(), finalHp, false);

        enemyEntity.addGold(blueprint.getGoldReward());
        
        return new AIRecord(enemyEntity, blueprint.getBehavior());
    }
}