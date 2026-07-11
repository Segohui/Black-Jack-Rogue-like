package blackjack.visual.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.dto.CardDrawEventData;
import blackjack.dto.EntityStateData;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

public class CardDrawScreen implements Screen {
    private final InputOutput io;
    private final BattleController controller;

    public CardDrawScreen(InputOutput io, BattleController controller) {
        this.io = io;
        this.controller = controller;
    }

    @Override
    public void render() {
        CardDrawEventData cardDrawData = controller.getDrawnCardEvent();
        EntityStateData entityData = controller.getEntityStateDataByName(cardDrawData.entityName());
        String entityName = cardDrawData.entityName();

        io.printHeader(entityName + " Turn", 15);
        io.printUpdate(cardDrawData.entityName() + " drew: " + cardDrawData.cardName());
        io.printLine();
        io.printDivider("=");
        io.printEntityState(entityData);
        io.printDivider("=");
        io.enterToProceed();
    }
}
