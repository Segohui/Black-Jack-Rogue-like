package blackjack.core.states;

import blackjack.entity.Enemy;
import blackjack.entity.Player;
import blackjack.entity.components.BehaviorComponent;
import blackjack.entity.components.CurrencyComponent;
import blackjack.entity.components.DeckComponent;
import blackjack.entity.components.HealthComponent;

public class StateFactory {
    private final HealthComponent playerHealthComponent;
    private final HealthComponent enemyHealthComponent;
    private final DeckComponent playerDeckComponent;
    private final DeckComponent enemyDeckComponent;
    private final BehaviorComponent enemyBehaviorComponent;
    private final CurrencyComponent playerCurrencyComponent;

    public StateFactory(Player player, Enemy enemy) {
        this.playerHealthComponent = player.getHealthComponent();
        this.enemyHealthComponent = enemy.getHealthComponent();
        this.playerDeckComponent = player.getDeckComponent();
        this.enemyDeckComponent = enemy.getDeckComponent();
        this.enemyBehaviorComponent = enemy.getBehaviorComponent();
        this.playerCurrencyComponent = player.getCurrencyComponent();
    }

    public State createStartRoundState() {
        return new StartRoundState(playerDeckComponent, enemyDeckComponent);
    }

    public State createPlayerTurnState() {
        return new PlayerTurnState(playerDeckComponent);
    }

    public State createPlayerOnlyState() {
        return new PlayerOnlyState(playerDeckComponent);
    }

    public State createEnemyTurnState() {
        return new EnemyTurnState(enemyDeckComponent, enemyBehaviorComponent);
    }

    public State createEnemyOnlyState() {
        return new EnemyOnlyTurnState(enemyDeckComponent, enemyBehaviorComponent);
    }

    public State createEndTurnState() {
        return new EndTurnState(playerHealthComponent, enemyHealthComponent, playerDeckComponent, enemyDeckComponent);
    }

    public State createEndGameState() {
        return new EndGameState(playerHealthComponent, playerDeckComponent, playerCurrencyComponent);
    }
}
