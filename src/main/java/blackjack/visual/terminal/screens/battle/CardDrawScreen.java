package blackjack.visual.terminal.screens.battle;

import blackjack.controller.BattleController;
import blackjack.dto.CardDrawEventData;
import blackjack.dto.EntityStateData;
import blackjack.visual.InputOutput;
import blackjack.visual.terminal.screens.Screen;

public class CardDrawScreen implements Screen {
    private final InputOutput io;
    private final BattleController controller;
    private final CardDrawEventData eventData;

    public CardDrawScreen(InputOutput io, BattleController controller, CardDrawEventData eventData) {
        this.io = io;
        this.controller = controller;
        this.eventData = eventData;
    }

    @Override
    public void render() {
        EntityStateData entityData = controller.getEntityStateDataByName(eventData.entityName());
        String entityName = eventData.entityName();

        io.printHeader(entityName + " Turn");
        io.printUpdate(eventData.entityName() + " drew: " + eventData.cardName());
        io.printLine();
        io.printDivider("=");
        io.printEntityState(entityData);
        io.printDivider("=");
        io.enterToProceed();
    }
}
