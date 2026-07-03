package blackjack.visual.terminal;

import blackjack.controller.BlackjackController;
import blackjack.dto.DamageEventData;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.ScreenFactory;
import blackjack.visual.terminal.screens.Screen;

public class BlackjackViewTerminal {
    private final ScreenFactory screenFactory;
    private final NotificationRenderer notifications;
    private final InputOutput io;
    private final BlackjackController controller;

    public BlackjackViewTerminal(InputOutput io, BlackjackController controller) {
        this.io = io;
        this.controller = controller;
        this.screenFactory = new ScreenFactory(io, controller);
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

    private void onCombatOver() {
        navigateToScreen(screenFactory.createCombatOverScreen());
    }

    private void onPlayerTurn() {
        navigateToScreen(screenFactory.createPlayerTurnScreen());
    }
}