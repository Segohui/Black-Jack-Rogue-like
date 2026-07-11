package blackjack.visual.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.dto.EntityStateData;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.ActionPrompter;
import blackjack.visual.terminal.screens.Screen;

import java.util.List;

public class PlayerTurnScreen implements Screen {
    private final InputOutput io;
    private final BattleController controller;

    public PlayerTurnScreen(InputOutput io, BattleController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        io.printHeader("Player Turn", 15);

        EntityStateData enemyData = controller.getEnemyData();
        EntityStateData playerData = controller.getPlayerData();

        io.printDivider("=");
        io.printEntityState(enemyData);
        io.printDivider("=");
        io.printEntityState(playerData);
        io.printDivider("=");
        

        ActionPrompter actionPrompter = new ActionPrompter(io);
        actionPrompter.addAction("hit", controller::playerHit);
        actionPrompter.addAction("stand", controller::playerStand);
        
        List<String> purchasedCards = controller.getPurchasedCardNames();

        if(!purchasedCards.isEmpty()){
            actionPrompter.addAction("use", this::promptPurchasedCard);
        }

        actionPrompter.promptAndRun();
    }

    private void promptPurchasedCard(){
        List<String> purchasedCards = controller.getPurchasedCardNames();
        ActionPrompter cardPrompter = new ActionPrompter(io);

        for(int i=0;i<purchasedCards.size();i++){
            int idx = i;
            cardPrompter.addAction(purchasedCards.get(i), () -> controller.playerUseBoughtCard(idx));
        }
        cardPrompter.defineBottomAction("Go back", this::render);
        cardPrompter.promptAndRun();
    }
}
