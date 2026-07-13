package blackjack.core.entity;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.cards.Card;
import blackjack.core.cards.Deck;
import blackjack.core.entity.components.CardsComponent;
import blackjack.core.entity.components.HealthComponent;
import blackjack.core.entity.components.ModifiersComponent;
import blackjack.core.entity.modifiers.DamageModifier;
import blackjack.core.entity.modifiers.SumModifier;
import blackjack.core.signal.DataSignal;
import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.DamageEventDTO;

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

    public CombatEntity(String name, Deck deck, int maxHp, boolean isPlayerControlled) {
        this.name = name;
        this.isPlayerControlled = isPlayerControlled;
        this.cardsComponent = new CardsComponent(deck);
        this.healthComponent = new HealthComponent(maxHp);
        this.modifiersComponent = new ModifiersComponent();
    }

    private CardDrawEventDTO createCardDrawEventData(Card card) {
        return new CardDrawEventDTO(card.toString(), name);
    }

    private DamageEventDTO createDamageEventData(int damageTaken) {
        return new DamageEventDTO(name, damageTaken);
    }

    @Override 
    public void clearSignals() {
        drawCard.clearConnections();
        entityStand.clearConnections();
        takeDamage.clearConnections();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return cardsComponent.getCards();
    }

    @Override
    public int calculateHandSum() {
        return cardsComponent.calculateHandSum();
    }
    
    @Override
    public List<Card> drawInitialCards(int amount) {
        return cardsComponent.drawCardToHand(amount);
    }

    @Override
    public void roundReset() {
        cardsComponent.resetHand();
    }

    @Override
    public void battleReset() {
        healthComponent.resetHp();
        cardsComponent.resetStack();
        hasStanded = false;
        roundReset();
    }

    @Override
    public void hit() {
        hasStanded = false;
        Card card = cardsComponent.drawCardToHand(1).get(0);
        emitDrawCard(createCardDrawEventData(card));
    }

    @Override
    public void stand() {
        hasStanded = true;
        emitEntityStand();
    }

    @Override
    public int getCurrentHp() {
        return healthComponent.getCurrentHp();
    }

    @Override
    public void takeDamage(int damage) {
        if (hasStanded) {
            damage = Math.ceilDiv(damage, 2);
        }
        
        healthComponent.takeDamage(damage);
        takeDamage.emit(createDamageEventData(damage));
    }

    @Override
    public void heal(int amount) {
        healthComponent.heal(amount);
    }

    @Override
    public boolean isAlive() {
        return (healthComponent.getCurrentHp() > 0);
    }

    @Override
    public boolean isPlayerControlled() {
        return isPlayerControlled;
    }

    @Override
    public Card discardLastCardInHand() {
        return cardsComponent.discardLastCardInHand();
    }

    @Override
    public int calculateAttackDamage() {
        int totalDamage = modifiersComponent.applyModifiers(getCards());
        modifiersComponent.clearModifiers();
        return totalDamage;
    }

    @Override
    public void addDamageOutputModifier(DamageModifier modifier) {
        modifiersComponent.addOutputModifier(modifier);
    }

    @Override
    public void addDamageCardModifier(Card card, DamageModifier modifier) {
        modifiersComponent.addCardModifier(card, modifier);
    }

    @Override
    public void addSumModifier(SumModifier modifier) {
        
        cardsComponent.addSumModifier(modifier);
    }

    @Override
    public Card peekNextCard() {
        return cardsComponent.peekNextCard();
    }

    @Override
    public List<Card> setHand(List<Card> newCards) {
        return cardsComponent.setHand(newCards);
    }

    // Signals Handling

    @Override
    public void drawCardConnect(Consumer<CardDrawEventDTO> listener) { drawCard.connect(listener); }

    @Override
    public void emitDrawCard(CardDrawEventDTO eventData) { drawCard.emit(eventData); }

    @Override
    public void entityStandConnect(Consumer<String> listener) { entityStand.connect(listener); }

    @Override
    public void emitEntityStand() { entityStand.emit(name); }
    
    @Override
    public void takeDamageConnect(Consumer<DamageEventDTO> listener) { takeDamage.connect(listener); }

    @Override
    public void emitTakeDamage(DamageEventDTO eventData) { takeDamage.emit(eventData); }
}
