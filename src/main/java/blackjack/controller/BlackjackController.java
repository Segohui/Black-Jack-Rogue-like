package blackjack.controller;

import java.util.List;

import blackjack.core.BlackjackCore;
import blackjack.core.cards.Card;
import blackjack.dto.CombatResultData;
import blackjack.dto.EntityStateData;
import blackjack.entity.Enemy;

public class BlackjackController {
    private final BlackjackCore core;

    public BlackjackController(BlackjackCore core) {
        this.core = core;
    }

    public void startCombat(Enemy enemy) {
        core.startRound(enemy);
    }

    public void gameOverConnect(Runnable runnable) {
        core.gameOverConnect(runnable);
    }

    public void nextTurnConnect(Runnable runnable) {
        core.nextTurnConnect(runnable);
    }

    public CombatResultData getCombatResult() {
        return new CombatResultData(getLastWinnerName(),
                core.getPlayerWins(), core.getPlayerLoses());
    }

    public EntityStateData getEnemyData() {
        return new EntityStateData(core.getEnemyName(),
                core.calculateEnemySum(),
                convertCardsToNames(core.getEnemyCards()));
    }

    public EntityStateData getPlayerData() {
        return new EntityStateData(core.getPlayerName(),
                core.calculatePlayerSum(),
                convertCardsToNames(core.getPlayerCards()));
    }

    public String getLastWinnerName() {
        if (core.getLastWinner() == null) {
            return "no one (tie)";
        }
        return core.getLastWinner().getName();
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
