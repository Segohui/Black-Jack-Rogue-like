package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.entity.Entity;
import blackjack.core.inventory.Inventory;

public class EndTurnState implements State {
    private final Entity player;
    private final Entity enemy;
    private final Inventory playerInventory;
    private final Inventory enemyInventory;

    public EndTurnState(Entity player, Entity enemy,
            Inventory playerInventory, Inventory enemyInventory) {
        this.player = player;
        this.enemy = enemy;
        this.playerInventory = playerInventory;
        this.enemyInventory = enemyInventory;
    }

    @Override
    public void handle(BattleCore core) {
        int playerSum = player.calculateHandSum();
        int enemySum = enemy.calculateHandSum();
        int globalStand = core.getGlobalStand();

        if (playerSum == enemySum) {
            endTurn(core, null);
            return;
        }

        if (playerWin(globalStand, playerSum, enemySum)) {
            playerInventory.triggerItemsAuto(core.getBattleContextDTO());
            enemy.takeDamage(player.calculateAttackDamage());
            if (!enemy.isAlive()) {
                core.activateEndGameState();
            } else {
                endTurn(core, enemy.getName());
            }
        } else {
            enemyInventory.triggerItemsAuto(core.getBattleContextDTO());
            player.takeDamage(enemy.calculateAttackDamage());
            if (!player.isAlive()) {
                core.activateEndGameState();
            } else {
                endTurn(core, enemy.getName());
            }
        }
    }

    private void endTurn(BattleCore core, String winnerName) {
        core.emitRoundOverData(winnerName);
        core.activateStartRoundState();
    }

    private boolean playerWin(int globalStand, int playerSum, int enemySum) {
        return ((playerSum <= globalStand)
                && (playerSum > enemySum || enemySum > globalStand));
    }
}
