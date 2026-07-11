package blackjack.visual.terminal.view;

import blackjack.controller.BattleController;
import blackjack.dto.CardDrawEventDTO;
import blackjack.dto.DamageEventDTO;
import blackjack.dto.CombatOverDTO;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;
import blackjack.visual.terminal.screens.battle.BattleScreenFactory;

public class BattleViewTerminal {
    private final BattleScreenFactory screenFactory;
    private final InputOutput io;
    private final BattleController controller;

    public BattleViewTerminal(InputOutput io, BattleController controller) {
        this.io = io;
        this.controller = controller;
        this.screenFactory = new BattleScreenFactory(io, controller);
        
        // Notifications
        controller.takeDamageConnect(this::onTakeDamage);
        controller.entityStandConnect(this::onEntityStand);

        // Screens
        controller.drawCardConnect(this::onDrawCard);
        controller.roundOverDataConnect(this::onRoundOver);
        controller.playerTurnConnect(this::onPlayerTurn);
        controller.combatOverDataConnect(this::onCombatOver);
    }

    private void navigateToScreen(Screen newScreen) {
        io.clearScreen();
        newScreen.render();
    }

    // Screens
    private void onDrawCard(CardDrawEventDTO eventData) {
        navigateToScreen(screenFactory.createCardDrawScreen(eventData));
    }

    private void onRoundOver(String winnerName) {
        navigateToScreen(screenFactory.createRoundOverScreen(winnerName));
    }

    private void onCombatOver(CombatOverDTO winnerDataDTO) {
        navigateToScreen(screenFactory.createCombatOverScreen(winnerDataDTO));
    }

    private void onPlayerTurn() {
        navigateToScreen(screenFactory.createPlayerTurnScreen());
    }

    private void onTakeDamage(DamageEventDTO eventData) {
        String message = eventData.targetName() + " took " + eventData.damage() + " damage!";
        navigateToScreen(screenFactory.createNotificationScreen(message));
    }

    private void onEntityStand(String name) {
        String message = name + " Stand!";
        navigateToScreen(screenFactory.createNotificationScreen(message));
    }
}