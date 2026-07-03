package blackjack.visual.terminal.screens;

import blackjack.controller.BlackjackController;
import blackjack.dto.CardDrawEventData;
import blackjack.dto.EntityStateData;
import blackjack.visual.InputOutput;

public class CardDrawScreen implements Screen {
    private final InputOutput io;
    private final BlackjackController controller;

    public CardDrawScreen(InputOutput io, BlackjackController controller) {
        this.io = io;
        this.controller = controller;
    }

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
