package blackjack.controller;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.DataSignal;
import blackjack.core.battle.BattleCore;
import blackjack.core.cards.Card;
import blackjack.dto.CardDrawEventData;
import blackjack.dto.DamageEventData;
import blackjack.dto.EntityStateData;
import blackjack.entity.AIRecord;

public class BattleController {
    private final BattleCore core;
    private final AIRecord aiRecord;

    private final DataSignal<Boolean> playerAlive = new DataSignal<>(); 

    public BattleController(BattleCore core, AIRecord aiRecord) {
        this.core = core;
        this.aiRecord = aiRecord;
        core.combatOverConnect(this::handleCoreGameOver);
    }

    public void startBattle() {
        core.startCombat(aiRecord);
    }

    private void handleCoreGameOver(boolean isPlayerAlive) {
        playerAlive.emit(isPlayerAlive);
    }

    public EntityStateData getEnemyData() {
        return new EntityStateData(core.getEnemyName(),
                core.calculateEnemySum(),
                convertCardsToNames(core.getEnemyCards()), core.getEnemyCurrentHp());
    }

    public EntityStateData getPlayerData() {
        return new EntityStateData(core.getPlayerName(),
                core.calculatePlayerSum(),
                convertCardsToNames(core.getPlayerCards()), core.getPlayerCurrentHp());
    }

    public EntityStateData getEntityStateDataByName(String entityName) {
        if (entityName.isEmpty() || entityName.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (entityName.equals(core.getPlayerName())) {
            return getPlayerData();
        } else {
            return getEnemyData();
        }
    }

    public DamageEventData getDamageEvent() {
        return core.getLastDamageEvent();
    }

    public String getWinnerName() {
        if (core.getWinner() == null) {
            return "no one (tie)";
        }
        return core.getWinner().getName();
    }

    public String getPlayerName() {
        return core.getPlayerName();
    }

    public CardDrawEventData getDrawnCardEvent() {
        return core.getLastDrawnCardEvent();
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

    public void playerUseBoughtCard(int idx){
        core.playerUsePurchasedCard(idx);
    }

    public int getLastGoldReward() {
        return core.getLastGoldReward();
    }

    public List<String> getPurchasedCardNames() {
        return core.getPlayer().getPurchasedCards().stream()
                .map(card -> card.toString())
                .toList();
    }

    // Connects

    public void roundOverConnect(Runnable runnable) {
        core.roundOverConnect(runnable);
    }

    public void playerTurnConnect(Runnable runnable) {
        core.playerTurnConnect(runnable);
    }

    public void takeDamageConnect(Runnable runnable) {
        core.takeDamageConnect(runnable);
    }

    public void enemyStandConnect(Runnable runnable) {
        core.enemyStandConnect(runnable);
    }

    public void drawCardConnect(Runnable runnable) {
        core.drawCardConnect(runnable);
    }

    public void combatOverConnect(Consumer<Boolean> listener) {
        core.combatOverConnect(listener);
    }

    public void playerAliveConnect(Consumer<Boolean> listener) {
        playerAlive.connect(listener);
    }
}
