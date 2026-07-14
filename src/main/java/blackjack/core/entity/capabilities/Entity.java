package blackjack.core.entity.capabilities;

public interface Entity extends IRoundParticipant, ISignalEmitter {
    String getName();
}