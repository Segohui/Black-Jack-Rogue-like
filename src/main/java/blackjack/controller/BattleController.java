package blackjack.controller;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.battle.BattleCore;
import blackjack.core.cards.Card;
import blackjack.core.inventory.Inventory;
import blackjack.core.inventory.ItemInfo;
import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.CombatOverDTO;
import blackjack.dtos.core.battle.DamageEventDTO;
import blackjack.dtos.core.battle.EntityStateDTO;
import blackjack.dtos.entity.AIRecordDTO;

public class BattleController {
    private final BattleCore core;
    private final Inventory playerInventory;

    public BattleController(BattleCore core, Inventory playerInventory) {
        this.core = core;
        this.playerInventory = playerInventory;
    }

    public void prepareBattleStage(AIRecordDTO aiRecord) {
        core.prepareForNewBattle(aiRecord);
    }

    public void startBattle() {
        core.startCombat();
    }

    public EntityStateDTO getEnemyData() {
        return new EntityStateDTO(core.getEnemyName(),
                core.calculateEnemySum(),
                convertCardsToNames(core.getEnemyCards()), core.getEnemyCurrentHp());
    }

    public EntityStateDTO getPlayerData() {
        return new EntityStateDTO(core.getPlayerName(),
                core.calculatePlayerSum(),
                convertCardsToNames(core.getPlayerCards()), core.getPlayerCurrentHp());
    }

    public EntityStateDTO getEntityStateDataByName(String entityName) {
        if (entityName.isEmpty() || entityName.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (entityName.equals(core.getPlayerName())) {
            return getPlayerData();
        } else {
            return getEnemyData();
        }
    }

    public String getPlayerName() {
        return core.getPlayerName();
    }

    public void playerHit() {
        core.playerHit();
    }

    public void playerStand() {
        core.playerStand();
    }

    private List<String> convertCardsToNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.toString())
                .toList();
    }

    public boolean playerHasItems() {
        return playerInventory.hasItems();
    }

    public void playerUseItem(int idx) {
        core.playerUseItem(idx);
    }

    public List<ItemInfo> getItemInfos() {
        return playerInventory.getItemInfos();
    }

    public List<String> getItemLines() {
        return playerInventory.getItemInfos().stream()
                .map(info -> "%s - %s".formatted(info.name(), info.description()))
                .toList();
    }

    // Connects

    public void roundOverDataConnect(Consumer<String> listener) {
        core.roundOverDataConnect(listener);
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

    public void combatOverDataConnect(Consumer<CombatOverDTO> listener) {
        core.combatOverDataConnect(listener);
    }
}
