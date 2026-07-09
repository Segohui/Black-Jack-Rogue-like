package blackjack.entity;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.entity.components.CardsComponent;
import blackjack.entity.components.CurrencyComponent;
import blackjack.entity.components.HealthComponent;

public class CombatEntity implements Entity {
    private final CardsComponent cardsComponent;
    private final HealthComponent healthComponent;
    private final CurrencyComponent currencyComponent;
    private final String name;

    public CombatEntity(String name, int maxHp) {
        this.name = name;
        this.cardsComponent = new CardsComponent();
        this.healthComponent = new HealthComponent(maxHp);
        this.currencyComponent = new CurrencyComponent(0);
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
        roundReset();
    }

    @Override
    public Card hit() {
        Card card = cardsComponent.drawCardToHand(1).get(0);
        return card;
    }

    @Override
    public int getCurrentHp() {
        return healthComponent.getCurrentHp();
    }

    @Override
    public void takeDamage(int damage) {
        healthComponent.takeDamage(damage);
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
    public void addPurchasedCard(Card card) {
        cardsComponent.addPurchasedCard(card);
    }

    @Override
    public Card usePurchasedCard(int idx){
        return cardsComponent.usePurchasedCard(idx);
    }

    @Override
    public boolean hasPurchasedCards(){
        return cardsComponent.hasPurchasedCards();
    }

    @Override
    public List<Card> getPurchasedCards(){
        return cardsComponent.getPurchasedCards();
    }

    @Override
    public int getGold(){
        return currencyComponent.getGold();
    }

    @Override
    public boolean canAfford(int cost) {
        return currencyComponent.canAfford(cost);
    }

    @Override
    public void spend(int cost){
        currencyComponent.spend(cost);
    }

    @Override
    public void addGold(int amount){
        currencyComponent.add(amount);
    }

}
