package blackjack.core.entity;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.core.cards.Deck;
import blackjack.core.entity.capabilities.Entity;
import blackjack.core.entity.components.CardsComponent;
import blackjack.core.entity.components.HealthComponent;
import blackjack.core.entity.components.ModifiersComponent;
import blackjack.core.entity.modifiers.DamageModifier;
import blackjack.core.entity.modifiers.SumModifier;
import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.DamageEventDTO;

/**
 * Concrete combat participant for player and enemy entities.
 *
 * <p>This class composes health, card, and modifier components and exposes
 * public battle behavior through the {@link Entity} capability contract.</p>
 */
public class CombatEntity implements Entity {
    private final DataSignal<CardDrawEventDTO> drawCard = new DataSignal<>();
    private final DataSignal<String> entityStand = new DataSignal<>();
    private final DataSignal<DamageEventDTO> takeDamage = new DataSignal<>();

    private final CardsComponent cardsComponent;
    private final HealthComponent healthComponent;
    private final ModifiersComponent modifiersComponent;
    private final String name;
    private final boolean isPlayerControlled;
    
    private boolean hasStanded = false;

    /**
     * Creates a combat entity with a deck, hit points, and control type.
     *
     * @param name name of the entity
     * @param deck deck used to draw cards
     * @param maxHp maximum health points for the entity
     * @param isPlayerControlled true when the entity is controlled by the player
     */
    public CombatEntity(String name, Deck deck, int maxHp, boolean isPlayerControlled) {
        this.name = name;
        this.isPlayerControlled = isPlayerControlled;
        this.cardsComponent = new CardsComponent(deck);
        this.healthComponent = new HealthComponent(maxHp);
        this.modifiersComponent = new ModifiersComponent();
    }

    /**
     * Builds the event data for a card draw signal.
     *
     * @param card card that was drawn
     * @return DTO containing draw event data
     */
    private CardDrawEventDTO createCardDrawEventData(Card card) {
        return new CardDrawEventDTO(card.toString(), name);
    }

    /**
     * Builds the event data for a damage event signal.
     *
     * @param damageTaken amount of damage taken by the entity
     * @return DTO containing damage event data
     */
    private DamageEventDTO createDamageEventData(int damageTaken) {
        return new DamageEventDTO(name, damageTaken);
    }

    /**
     * Clears any connected event listeners of this entity.
     */
    @Override
    public void clearSignals() {
        drawCard.clearConnections();
        entityStand.clearConnections();
        takeDamage.clearConnections();
    }

    /**
     * Returns the entity name.
     *
     * @return entity name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the cards currently held by the entity.
     *
     * @return list of cards in hand
     */
    @Override
    public List<Card> getCards() {
        return cardsComponent.getCards();
    }

    /**
     * Calculates the current value of the entity's hand.
     *
     * @return hand sum value
     */
    @Override
    public int calculateHandSum() {
        return cardsComponent.calculateHandSum();
    }
    
    /**
     * Draws the initial cards for combat from the deck.
     *
     * @param amount number of cards to draw
     * @return list of drawn cards
     */
    @Override
    public List<Card> drawInitialCards(int amount) {
        return cardsComponent.drawCardToHand(amount);
    }

    /**
     * Resets the hand for the next round.
     */
    @Override
    public void roundReset() {
        cardsComponent.resetHand();
    }

    /**
     * Resets the entity for a new battle.
     */
    @Override
    public void battleReset() {
        healthComponent.resetHp();
        cardsComponent.resetStack();
        hasStanded = false;
        roundReset();
    }

    /**
     * Performs a hit action by drawing a card and emitting a draw event.
     */
    @Override
    public void hit() {
        hasStanded = false;
        Card card = cardsComponent.drawCardToHand(1).get(0);
        emitDrawCard(createCardDrawEventData(card));
    }

    /**
     * Performs a stand action and emits a stand event.
     */
    @Override
    public void stand() {
        hasStanded = true;
        emitEntityStand(name);
    }

    /**
     * Returns the current hit points.
     *
     * @return current health points
     */
    @Override
    public int getCurrentHp() {
        return healthComponent.getCurrentHp();
    }

    /**
     * Applies damage to the entity and emits a damage event.
     *
     * @param damage raw damage amount before modifiers
     */
    @Override
    public void takeDamage(int damage) {
        if (hasStanded) {
            damage = Math.ceilDiv(damage, 2);
        }
        
        healthComponent.takeDamage(damage);
        takeDamage.emit(createDamageEventData(damage));
    }

    /**
     * Heals the entity by the specified amount.
     *
     * @param amount amount of health to restore
     */
    @Override
    public void heal(int amount) {
        healthComponent.heal(amount);
    }

    /**
     * Returns whether the entity is alive.
     *
     * @return true when current HP is greater than zero
     */
    @Override
    public boolean isAlive() {
        return (healthComponent.getCurrentHp() > 0);
    }

    /**
     * Returns whether this entity is controlled by the player.
     *
     * @return true when the entity is player controlled
     */
    @Override
    public boolean isPlayerControlled() {
        return isPlayerControlled;
    }

    @Override
    public Card discardLastCardInHand() {
        return cardsComponent.discardLastCardInHand();
    }

    /**
     * Calculates attack damage using active modifiers and clears them.
     *
     * @return computed attack damage
     */
    @Override
    public int calculateAttackDamage() {
        int totalDamage = modifiersComponent.applyModifiers(getCards());
        modifiersComponent.clearModifiers();
        return totalDamage;
    }

    /**
     * Adds a damage modifier that affects the next output damage.
     *
     * @param modifier damage modifier to apply
     */
    @Override
    public void addDamageOutputModifier(DamageModifier modifier) {
        modifiersComponent.addOutputModifier(modifier);
    }

    /**
     * Adds a card-specific damage modifier.
     *
     * @param card the card to modify
     * @param modifier damage modifier applied to the card
     */
    @Override
    public void addDamageCardModifier(Card card, DamageModifier modifier) {
        modifiersComponent.addCardModifier(card, modifier);
    }

    /**
     * Adds a sum modifier that changes hand evaluation.
     *
     * @param modifier modifier that adjusts hand sum calculation
     */
    @Override
    public void addSumModifier(SumModifier modifier) {
        cardsComponent.addSumModifier(modifier);
    }

    /**
     * Peeks at the next card in the entity's deck without drawing it.
     *
     * @return next card from the deck
     */
    @Override
    public Card peekNextCard() {
        return cardsComponent.peekNextCard();
    }

    /**
     * Replaces the entity's hand with the specified cards.
     *
     * @param newCards new cards to place in hand
     * @return updated hand list
     */
    @Override
    public List<Card> setHand(List<Card> newCards) {
        return cardsComponent.setHand(newCards);
    }

    // Signals Handling

    /**
     * Connects a listener for card draw events.
     *
     * @param listener callback invoked when a card is drawn
     */
    @Override
    public void drawCardConnect(Consumer<CardDrawEventDTO> listener) { drawCard.connect(listener); }

    /**
     * Emits a card draw event.
     *
     * @param eventData data describing the drawn card
     */
    @Override
    public void emitDrawCard(CardDrawEventDTO eventData) { drawCard.emit(eventData); }

    /**
     * Connects a listener for stand events.
     *
     * @param listener callback invoked when the entity stands
     */
    @Override
    public void entityStandConnect(Consumer<String> listener) { entityStand.connect(listener); }

    /**
     * Emits a stand event for this entity.
     *
     * @param name name of the entity that stood
     */
    @Override
    public void emitEntityStand(String name) { entityStand.emit(name); }
    
    /**
     * Connects a listener for damage events.
     *
     * @param listener callback invoked when the entity takes damage
     */
    @Override
    public void takeDamageConnect(Consumer<DamageEventDTO> listener) { takeDamage.connect(listener); }

    /**
     * Emits a damage event for this entity.
     *
     * @param eventData damage event details
     */
    @Override
    public void emitTakeDamage(DamageEventDTO eventData) { takeDamage.emit(eventData); }
}
