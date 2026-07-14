package blackjack.view.terminal.views;

import blackjack.controller.BattleController;
import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.CombatOverDTO;
import blackjack.dtos.core.battle.DamageEventDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.view.terminal.io.InputOutput;
import blackjack.view.terminal.screens.Screen;
import blackjack.view.terminal.screens.battle.BattleScreenFactory;

public class BattleViewTerminal {
    private final InputOutput io;
    private final BattleScreenFactory screenFactory;
    
    public BattleViewTerminal(InputOutput io, BattleController controller) {
        this.io = io;
        this.screenFactory = new BattleScreenFactory(io, controller);
        
        // Notifications
        controller.takeDamageConnect(this::onTakeDamage);
        controller.entityStandConnect(this::onEntityStand);
        controller.itemTriggeredConnect(this::onItemTriggered);

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

    private void onTakeDamage(DamageEventDTO eventDTO) {
        navigateToScreen(screenFactory.createTakeDamageScreen(eventDTO));
    }

    private void onEntityStand(String entityName) {
        navigateToScreen(screenFactory.createEntityStandScreen(entityName));
    }

    private void onItemTriggered(ItemInfoDTO itemInfo) {
        navigateToScreen(screenFactory.createActionNotificationScreen(itemInfo.actionText()));
    }
}