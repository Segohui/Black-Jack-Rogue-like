package blackjack.controller;

import java.util.List;

import blackjack.core.BlackjackCore;
import blackjack.core.cards.Card;
import blackjack.core.shop.Shop;
import blackjack.dto.CardDrawEventData;
import blackjack.dto.DamageEventData;
import blackjack.dto.EntityStateData;
import blackjack.entity.AIRecord;

public class BlackjackController {
    private final BlackjackCore core;

    public BlackjackController(BlackjackCore core) {
        this.core = core;
    }

    public void startCombat(AIRecord enemyRecord) {
        core.startCombat(enemyRecord);
    }

    public void roundOverConnect(Runnable runnable) {
        core.roundOverConnect(runnable);
    }

    public void playerTurnConnect(Runnable runnable) {
        core.playerTurnConnect(runnable);
    }

    public void combatOverConnect(Runnable runnable) {
        core.combatOverConnect(runnable);
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

    public CardDrawEventData getDrawnCardEvent() {
        return core.getLastDrawnCardEvent();
    }

    public void playerHit() {
        core.playerHit();
    }

    public void playerStand() {
        core.playerStand();
    }

    public void playerUseBoughtCard(int idx){
        core.playerUsePurchasedCard(idx);
    }

    public boolean playerHasPurchasedCards(){
        return core.getPlayer().hasPurchasedCards();
    }

    public List<String> getPurchasedCardNames() {
        return core.getPlayer().getPurchasedCards().stream()
                .map(card -> card.toString())
                .toList();
    }

    public List<String> getShopItemLines(Shop shop) {
        return shop.getItemsForSale().stream()
                .map(item -> item.getName() + " (" + item.getCost() + "g) - " + item.getDescription())
                .toList();
    }

    public int getPlayerGold() {
        return core.getPlayer().getGold();
    }

    public boolean buyShopItem(Shop shop, int index) {
        return shop.buy(index, core.getPlayer());
    }

    private List<String> convertCardsToNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.toString())
                .toList();
    }

    public int getLastGoldReward(){
        return core.getLastGoldReward();
    }
}
