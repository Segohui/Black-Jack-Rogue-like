package blackjack.controller;

import java.util.List;

import blackjack.core.BlackjackCore;
import blackjack.core.cards.Card;
import blackjack.dto.CardDrawEventData;
import blackjack.dto.DamageEventData;
import blackjack.dto.EntityStateData;
import blackjack.entity.Enemy;

public class BlackjackController {
    private final BlackjackCore core;

    public BlackjackController(BlackjackCore core) {
        this.core = core;
    }

    public void startCombat(Enemy enemy) {
        core.startCombat(enemy);
    }

    public void roundOverConnect(Runnable runnable) {
        core.roundOverConnect(runnable);
    }

    public void nextTurnConnect(Runnable runnable) {
        core.nextTurnConnect(runnable);
    }

    public void gameOverConnect(Runnable runnable) {
        core.gameOverConnect(runnable);
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

    private List<String> convertCardsToNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.toString())
                .toList();
    }
}
