package blackjack.entity.enemy.factory;

import java.util.concurrent.ThreadLocalRandom;

import blackjack.core.cards.Deck;
import blackjack.core.inventory.Inventory;
import blackjack.dtos.entity.AIRecordDTO;
import blackjack.entity.CombatEntity;
import blackjack.entity.Entity;
import blackjack.entity.enemy.blueprints.MineEnemies;
import blackjack.entity.enemy.blueprints.EnemyBlueprint;

public class MineEnemiesFactory implements AbstractEnemyFactory {

    @Override
    public AIRecordDTO generateRandomEnemy(float difficultyMultiplier) {
        MineEnemies[] enemyPool = MineEnemies.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(enemyPool.length);
        EnemyBlueprint blueprint = enemyPool[randomIndex];

        return buildEnemy(blueprint, difficultyMultiplier);
    }

    @Override
    public AIRecordDTO generateSpecificEnemy(EnemyBlueprint blueprint, float difficultyMultiplier) {
        return buildEnemy(blueprint, difficultyMultiplier);
    }

    private AIRecordDTO buildEnemy(EnemyBlueprint blueprint, float multiplier) {
        int finalHp = (int) (blueprint.getBaseHp() * multiplier);
        
        Entity enemyEntity = new CombatEntity(
                blueprint.getName(), new Deck(), finalHp, false);

        Inventory enemyInventory = new Inventory();
        enemyInventory.addGold(blueprint.getGoldReward());
        
        return new AIRecordDTO(enemyEntity, blueprint.getBehavior(), enemyInventory);
    }
}