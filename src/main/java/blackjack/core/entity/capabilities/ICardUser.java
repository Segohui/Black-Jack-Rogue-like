package blackjack.core.entity.capabilities;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.core.entity.modifiers.SumModifier;

/**
 * Defines the card-hand behavior expected from a combat participant.
 */
public interface ICardUser {
    List<Card> getCards();
    int calculateHandSum();
    List<Card> drawInitialCards(int amount);
    void hit();
    void stand();
    Card discardLastCardInHand();
    Card peekNextCard();
    List<Card> setHand(List<Card> newCards);
    void roundReset();
    void addSumModifier(SumModifier modifier);
}