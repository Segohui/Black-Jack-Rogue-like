package blackjack.core.entity.capabilities;

import java.util.function.Consumer;

import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.DamageEventDTO;

/**
 * Defines signal hooks used to notify the UI layer about battle events.
 */
public interface ISignalEmitter {
    void clearSignals();
    void drawCardConnect(Consumer<CardDrawEventDTO> listener);
    void entityStandConnect(Consumer<String> listener);
    void takeDamageConnect(Consumer<DamageEventDTO> listener);
    void emitDrawCard(CardDrawEventDTO eventDTO);
    void emitEntityStand(String name);
    void emitTakeDamage(DamageEventDTO eventDTO);
}
