package blackjack.controller;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.battle.BattleCore;
import blackjack.core.inventory.Inventory;
import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.CombatOverDTO;
import blackjack.dtos.core.battle.DamageEventDTO;
import blackjack.dtos.core.battle.EntityStateDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.dtos.entity.AIRecordDTO;

public class BattleController {
    private final BattleCore core;
    private final Inventory playerInventory;

    public BattleController(BattleCore core, Inventory playerInventory) {
        this.core = core;
        this.playerInventory = playerInventory;
        playerInventory.clearItemPeekedConnections();
    }

    public void startBattle() {
        core.startCombat();
    }

    public void prepareBattleStage(AIRecordDTO aiRecord) {
        core.prepareForNewBattle(aiRecord);
    }

    public EntityStateDTO getEnemyData() {
        return core.getEnemyData();
    }

    public EntityStateDTO getPlayerData() {
        return core.getPlayerData();
    }

    public EntityStateDTO getEntityDataByName(String name) {
        return core.getEntityStateDataByName(name);
    }

    public void playerHit() {
        core.playerHit();
    }

    public void playerStand() {
        core.playerStand();
    }

    public boolean playerHasItems() {
        return playerInventory.hasItems();
    }

    public void playerUseItem(int idx) {
        core.playerUseItem(idx);
    }

    public List<ItemInfoDTO> getItemInfos() {
        return playerInventory.getItemInfos();
    }

    // Connects

    public void roundOverConnect(Consumer<String> listener) {
        core.roundOverConnect(listener);
    }

    public void playerTurnConnect(Runnable runnable) {
        core.playerTurnConnect(runnable);
    }

    public void takeDamageConnect(Consumer<DamageEventDTO> listener) {
        core.playerTakeDamageConnect(listener);
        core.enemyTakeDamageConnect(listener);
    }

    public void entityStandConnect(Consumer<String> listener) {
        core.playerStandConnect(listener);
        core.enemyStandConnect(listener);
    }

    public void drawCardConnect(Consumer<CardDrawEventDTO> listener) {
        core.drawCardPlayerConnect(listener);
        core.drawCardEnemyConnect(listener);
    }

    public void combatOverConnect(Consumer<CombatOverDTO> listener) {
        core.combatOverConnect(listener);
    }

    public void gameOverConnect(Consumer<Boolean> listener) {
        core.gameOverConnect(listener);
    }

    public void itemPeekedConnect(Consumer<String> listener) {
        playerInventory.itemPeekedConnect(listener);
    }       
}
