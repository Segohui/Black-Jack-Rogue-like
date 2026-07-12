package blackjack.controller;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.battle.BattleCore;
import blackjack.core.cards.Card;
import blackjack.dto.CardDrawEventDTO;
import blackjack.dto.CombatOverDTO;
import blackjack.dto.DamageEventDTO;
import blackjack.dto.EntityStateDTO;
import blackjack.entity.enemy.AIRecord;

public class BattleController {
    private final BattleCore core;

    public BattleController(BattleCore core) {
        this.core = core;
    }

    public void initializeEnemy(AIRecord aiRecord) {
        core.resetEnemy(aiRecord);
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

    public void playerUseBoughtCard(int idx){
        core.playerUsePurchasedCard(idx);
    }

    public List<String> getPurchasedCardNames() {
        return core.getPlayer().getPurchasedCards().stream()
                .map(card -> card.toString())
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
