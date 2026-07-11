package blackjack.visual.terminal.view;

import blackjack.controller.BattleController;
import blackjack.dto.DamageEventData;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.NotificationRenderer;
import blackjack.visual.terminal.screens.Screen;
import blackjack.visual.terminal.screens.battle.BattleScreenFactory;

public class BattleViewTerminal {
    private final BattleScreenFactory screenFactory;
    private final NotificationRenderer notifications;
    private final InputOutput io;
    private final BattleController controller;

    public BattleViewTerminal(InputOutput io, BattleController controller) {
        this.io = io;
        this.controller = controller;
        this.screenFactory = new BattleScreenFactory(io, controller);
        this.notifications = new NotificationRenderer(io);
        
        // Notifications
        controller.takeDamageConnect(this::onTakeDamage);
        controller.enemyStandConnect(this::onEnemyStand);

        // Screens
        controller.drawCardConnect(this::onDrawCard);
        controller.roundOverConnect(this::onRoundOver);
        controller.playerTurnConnect(this::onPlayerTurn);
        controller.combatOverConnect(this::onCombatOver);
    }

    private void navigateToScreen(Screen newScreen) {
        io.clearScreen();
        newScreen.render();
    }

    // Notifications
    private void onTakeDamage() {
        DamageEventData damageEvent = controller.getDamageEvent();
        String message = damageEvent.targetName() + " took " + damageEvent.damage() + " damage!";

        notifications.showPopup(message);
    }

    private void onEnemyStand() {
        String message = "Enemy Stand!";
        
        notifications.showPopup(message);
    }

    // Screens
    private void onDrawCard() {
        navigateToScreen(screenFactory.createCardDrawScreen());
    }

    private void onRoundOver() {
        navigateToScreen(screenFactory.createRoundOverScreen());
    }

    private void onCombatOver(Boolean isPlayerAlive) {
        navigateToScreen(screenFactory.createCombatOverScreen());
    }

    private void onPlayerTurn() {
        navigateToScreen(screenFactory.createPlayerTurnScreen());
    }
}