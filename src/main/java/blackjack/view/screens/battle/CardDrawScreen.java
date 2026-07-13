package blackjack.view.screens.battle;

import blackjack.controller.BattleController;
import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.EntityStateDTO;
import blackjack.view.InputOutput;
import blackjack.view.screens.Screen;

public class CardDrawScreen implements Screen {
    private final InputOutput io;
    private final BattleController controller;
    private final CardDrawEventDTO eventData;

    public CardDrawScreen(InputOutput io, BattleController controller, CardDrawEventDTO eventData) {
        this.io = io;
        this.controller = controller;
        this.eventData = eventData;
    }

    @Override
    public void render() {
        EntityStateDTO entityData = controller.getEntityStateDataByName(eventData.entityName());
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
