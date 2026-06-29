package blackjack.core;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.dto.CombatResultData;
import blackjack.dto.EntityStateData;
import blackjack.entity.Enemy;
import blackjack.visual.terminal.BlackjackViewTerminal;

public class BlackjackController {
    private final BlackjackCore core;
    private final BlackjackViewTerminal view;

    public BlackjackController(BlackjackViewTerminal view, BlackjackCore core) {
        this.core = core;
        this.view = view;
        this.core.gameOverConnect(this::onGameOver);
        this.core.nextTurnConnect(this::takePlayerTurn);
    }

    public void startCombat(Enemy enemy) {
        core.startRound(enemy);
        takePlayerTurn();
    }

    public void onGameOver() {
        updateView();
        CombatResultData result = new CombatResultData(getLastWinnerName(), core.getPlayerWins(), core.getPlayerLoses());
        view.gameEndScreen(result);
    }

    public String getLastWinnerName() {
        if (core.getLastWinner() == null) {
            return "no one (tie)";
        }
        return core.getLastWinner().getName();
    }

    public void takePlayerTurn() {
        while (true) {
            updateView();
            String input = view.readPlayerInput();
            if (input.equals("hit")) {
                core.playerHit();
            } else if (input.equals("stand")) {
                core.playerStand();
            } else {
                continue;
            }

            return;
        }
    }

    private void updateView() {
        List<String> enemyCardsStr = convertCardsToNames(core.getEnemyCards());
        List<String> playerCardsStr = convertCardsToNames(core.getPlayerCards());

        EntityStateData enemyData = new EntityStateData(
                core.getEnemyName(), core.calculateEnemySum(), enemyCardsStr);
        EntityStateData playerData = new EntityStateData(
                core.getPlayerName(), core.calculatePlayerSum(), playerCardsStr);

        view.entitiesHandsScreen(enemyData, playerData);
    }

    private List<String> convertCardsToNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.toString())
                .toList();
    }
}
