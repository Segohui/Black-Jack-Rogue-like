package blackjack.entity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import blackjack.entity.behaviors.ThresholdBehavior;

public class EnemyFactory {
    private final EnumMap<EnemyType, Function<Float, AIRecord>> enemyTypes;
    private int hpReference = 10;

    public EnemyFactory() {
        this.enemyTypes = new EnumMap<>(EnemyType.class);
        enemyTypes.put(EnemyType.SAFE, this::generateSafeEnemy);
        enemyTypes.put(EnemyType.MODERATE, this::generateModerateEnemy);
        enemyTypes.put(EnemyType.AGGRESSIVE, this::generateAggressiveEnemy);
    }

    public AIRecord generateSpecificEnemy(float multiplier, EnemyType type) {
        Function<Float, AIRecord> enemyGenerator = enemyTypes.get(type);
        return enemyGenerator.apply(multiplier);
    }

    public AIRecord generateRandomEnemy(float multiplier) {
        int randomIndex = ThreadLocalRandom.current().nextInt(EnemyType.values().length);
        EnemyType type = EnemyType.values()[randomIndex];

        Function<Float, AIRecord> enemyGenerator = enemyTypes.get(type);
    
        return enemyGenerator.apply(multiplier);
    }

    public List<AIRecord> generateThreeRandomEnemy(float multiplier) {
        List<AIRecord> enemies = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(EnemyType.values().length);
            EnemyType type = EnemyType.values()[randomIndex];

            Function<Float, AIRecord> enemyGenerator = enemyTypes.get(type);
            AIRecord enemy = enemyGenerator.apply(multiplier);
            enemies.add(enemy);
        }
        
        return enemies;
    }

    private AIRecord generateSafeEnemy(float multiplier) {
        int baseHp = calculateHp(3, multiplier);
        int standThreshold = 6;
        String name = "Villager";

        Entity enemy = new CombatEntity(name, Math.round(baseHp * multiplier));
        Behavior behavior = new ThresholdBehavior(enemy, standThreshold);
        return new AIRecord(enemy, behavior);
    }

    private AIRecord generateModerateEnemy(float multiplier) {
        int baseHp = calculateHp(5, multiplier);
        int standThreshold = 4;
        String name = "Zombie";

        Entity enemy = new CombatEntity(name, Math.round(baseHp * multiplier));
        Behavior behavior = new ThresholdBehavior(enemy, standThreshold);
        return new AIRecord(enemy, behavior);
    }

    private AIRecord generateAggressiveEnemy(float multiplier) {
        int baseHp = calculateHp(7, multiplier);
        int standThreshold = 3;
        String name = "Herobrine";

        Entity enemy = new CombatEntity(name, Math.round(baseHp * multiplier));
        Behavior behavior = new ThresholdBehavior(enemy, standThreshold);
        return new AIRecord(enemy, behavior);
    }

    private int calculateHp(float behaviorMultiplier, float multiplier) {
        return Math.round(hpReference * behaviorMultiplier * multiplier);
    }
}
