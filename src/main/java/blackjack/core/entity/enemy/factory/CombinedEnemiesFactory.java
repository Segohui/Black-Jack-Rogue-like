package blackjack.core.entity.enemy.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import blackjack.core.cards.Deck;
import blackjack.core.entity.CombatEntity;
import blackjack.core.entity.capabilities.Entity;
import blackjack.core.entity.enemy.blueprints.EnemyBlueprint;
import blackjack.core.entity.enemy.blueprints.IsaacEnemies;
import blackjack.core.entity.enemy.blueprints.MineEnemies;
import blackjack.core.inventory.Inventory;
import blackjack.dtos.entity.AIRecordDTO;

/**
 * Builds enemy AI records from a mixed pool of blueprint sets.
 */
public class CombinedEnemiesFactory implements AbstractEnemyFactory {
    private final List<EnemyBlueprint> blueprints = buildBlueprintPool();

    /**
     * Creates a random enemy using the combined blueprint pool.
     *
     * @param difficultyMultiplier scaling factor applied to enemy HP
     * @return generated AI record for the enemy
     */
    @Override
    public AIRecordDTO generateRandomEnemy(float difficultyMultiplier) {
        int randomIndex = ThreadLocalRandom.current().nextInt(blueprints.size());
        EnemyBlueprint blueprint = blueprints.get(randomIndex);

        return buildEnemy(blueprint, difficultyMultiplier);
    }

    @Override
    public AIRecordDTO generateSpecificEnemy(EnemyBlueprint blueprint, float difficultyMultiplier) {
        return buildEnemy(blueprint, difficultyMultiplier);
    }

    private List<EnemyBlueprint> buildBlueprintPool() {
        List<EnemyBlueprint> pool = new ArrayList<>();
        pool.addAll(List.of(MineEnemies.values()));
        pool.addAll(List.of(IsaacEnemies.values()));

        // Add more blueprints to increase pool diversity

        return pool;
    }

    private AIRecordDTO buildEnemy(EnemyBlueprint blueprint, float multiplier) {
        int finalHp = (int) (blueprint.getBaseHp() * multiplier);

        Entity enemyEntity = new CombatEntity(
                blueprint.getName(), new Deck(),finalHp, false);
        
        Inventory enemyInventory = new Inventory();
        enemyInventory.addGold(blueprint.getGoldReward());

        return new AIRecordDTO(enemyEntity, blueprint.getBehavior(), enemyInventory);
    }
}