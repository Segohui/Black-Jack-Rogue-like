package blackjack.core.battle.states;

import blackjack.core.battle.BattleCore;
import blackjack.core.entity.capabilities.IRoundParticipant;
import blackjack.core.inventory.Inventory;

public class EndRoundState implements State {
    private final IRoundParticipant player;
    private final IRoundParticipant enemy;
    private final Inventory playerInventory;
    private final Inventory enemyInventory;

    public EndRoundState(IRoundParticipant player, IRoundParticipant enemy,
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
            endRound(core, null);
            return;
        }

        if (playerWin(globalStand, playerSum, enemySum)) {
            playerInventory.triggerItemsAuto(core.getBattleContextDTO());
            enemy.takeDamage(player.calculateAttackDamage());
            if (!enemy.isAlive()) {
                core.activateEndGameState();
            } else {
                endRound(core, player.getName());
            }
        } else {
            enemyInventory.triggerItemsAuto(core.getBattleContextDTO());
            player.takeDamage(enemy.calculateAttackDamage());
            if (!player.isAlive()) {
                core.activateEndGameState();
            } else {
                endRound(core, enemy.getName());
            }
        }
    }

    private void endRound(BattleCore core, String winnerName) {
        core.emitRoundOverData(winnerName);
        core.activateStartRoundState();
    }

    private boolean playerWin(int globalStand, int playerSum, int enemySum) {
        return ((playerSum <= globalStand)
                && (playerSum > enemySum || enemySum > globalStand));
    }
}
