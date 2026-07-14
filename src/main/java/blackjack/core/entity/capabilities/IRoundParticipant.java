package blackjack.core.entity.capabilities;

/**
 * Represents an entity that participates in a battle round.
 */
public interface IRoundParticipant extends ICardUser, IDamageable, ICombatant {
    String getName();
    boolean isPlayerControlled();
}