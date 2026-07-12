package blackjack.entity.enemy.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import blackjack.core.cards.Deck;
import blackjack.entity.CombatEntity;
import blackjack.entity.Entity;
import blackjack.entity.enemy.AIRecord;
import blackjack.entity.enemy.blueprints.EnemyBlueprint;
import blackjack.entity.enemy.blueprints.IsaacEnemies;
import blackjack.entity.enemy.blueprints.MineEnemies;

public class CombinedEnemiesFactory implements AbstractEnemyFactory {
    private final List<EnemyBlueprint> blueprints = buildBlueprintPool();

    @Override
    public AIRecord generateRandomEnemy(float difficultyMultiplier) {
        int randomIndex = ThreadLocalRandom.current().nextInt(blueprints.size());
        EnemyBlueprint blueprint = blueprints.get(randomIndex);

        return buildEnemy(blueprint, difficultyMultiplier);
    }

    @Override
    public AIRecord generateSpecificEnemy(EnemyBlueprint blueprint, float difficultyMultiplier) {
        return buildEnemy(blueprint, difficultyMultiplier);
    }

    private List<EnemyBlueprint> buildBlueprintPool() {
        List<EnemyBlueprint> pool = new ArrayList<>();
        pool.addAll(List.of(MineEnemies.values()));
        pool.addAll(List.of(IsaacEnemies.values()));
        return pool;
    }

    private AIRecord buildEnemy(EnemyBlueprint blueprint, float multiplier) {
        int finalHp = (int) (blueprint.getBaseHp() * multiplier);

        Entity enemyEntity = new CombatEntity(
                blueprint.getName(), new Deck(),finalHp, false);

        enemyEntity.addGold(blueprint.getGoldReward());

        return new AIRecord(enemyEntity, blueprint.getBehavior());
    }
}