package blackjack.entity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class EnemyFactory {
    private final EnumMap<EnemyType, Function<Float, Enemy>> enemyTypes;

    public EnemyFactory() {
        this.enemyTypes = new EnumMap<>(EnemyType.class);
        enemyTypes.put(EnemyType.SAFE, this::generateSafeEnemy);
        enemyTypes.put(EnemyType.MODERATE, this::generateModerateEnemy);
        enemyTypes.put(EnemyType.AGGRESSIVE, this::generateAggressiveEnemy);
    }

    public Enemy generateSpecificEnemy(float multiplier, EnemyType type) {
        Function<Float, Enemy> enemyGenerator = enemyTypes.get(type);
        return enemyGenerator.apply(multiplier);
    }

    public Enemy generateRandomEnemy(float multiplier) {
        int randomIndex = ThreadLocalRandom.current().nextInt(EnemyType.values().length);
        EnemyType type = EnemyType.values()[randomIndex];

        Function<Float, Enemy> enemyGenerator = enemyTypes.get(type);
    
        return enemyGenerator.apply(multiplier);
    }

    public List<Enemy> generateThreeRandomEnemy(float multiplier) {
        List<Enemy> enemies = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(EnemyType.values().length);
            EnemyType type = EnemyType.values()[randomIndex];

            Function<Float, Enemy> enemyGenerator = enemyTypes.get(type);
            Enemy enemy = enemyGenerator.apply(multiplier);
            enemies.add(enemy);
        }
        
        return enemies;
    }

    private Enemy generateSafeEnemy(float multiplier) {
        int baseHp = 30;
        int standThreshold = 6;
        String name = "Jonas";

        return new Enemy(Math.round(baseHp * multiplier), standThreshold, name);
    }

    private Enemy generateModerateEnemy(float multiplier) {
        int baseHp = 50;
        int standThreshold = 4;
        String name = "Malaquias";

        return new Enemy(Math.round(baseHp * multiplier), standThreshold, name);
    }

    private Enemy generateAggressiveEnemy(float multiplier) {
        int baseHp = 70;
        int standThreshold = 3;
        String name = "Amélia";

        return new Enemy(Math.round(baseHp * multiplier), standThreshold, name);
    }
}
