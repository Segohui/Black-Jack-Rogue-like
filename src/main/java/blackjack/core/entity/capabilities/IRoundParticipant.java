package blackjack.core.entity.capabilities;

public interface IRoundParticipant extends ICardUser, IDamageable, ICombatant {
    String getName();
    boolean isPlayerControlled();
}