package blackjack.core;

import java.util.List;

import blackjack.core.cards.Card;
import blackjack.core.states.State;
import blackjack.core.states.StateFactory;
import blackjack.dto.CardDrawEventData;
import blackjack.dto.DamageEventData;
import blackjack.entity.CombatEntity;
import blackjack.entity.Enemy;
import blackjack.entity.Player;

public class BlackjackCore {
    private final Signal playerTurn = new Signal();
    private final Signal roundOver = new Signal();
    private final Signal combatOver = new Signal();
    private final Signal takeDamage = new Signal();
    private final Signal enemyStand = new Signal();
    private final Signal drawCard = new Signal();

    private final Player player;
    private int globalStand = 21; // may change with power ups

    private Enemy enemy;
    private StateFactory stateFactory;
    private CombatEntity winner;
    private DamageEventData lastDamageEvent;
    private CardDrawEventData lastCardDrawEvent;
    private State state;

    public BlackjackCore(Player player) {
        this.player = player;
    }

    public void startCombat(Enemy enemy) {
        resetCore();
        this.enemy = enemy;
        this.stateFactory = new StateFactory(player, enemy);
        activateStartRoundState();
    }

    public void resetCore() {
        this.lastDamageEvent = null;
        this.lastCardDrawEvent = null;
        this.state = null;
        this.winner = null;
        this.enemy = null;
        this.stateFactory = null;
    }

    public void playerHit() {
        state.hit(this);
    }

    public void playerStand() {
        state.stand(this);
    }

    public void playerUsePurchasedCard(int idx){
        state.useBoughtCard(this, idx);
    }

    public int calculatePlayerSum() {
        return player.getDeckComponent().calculateHandSum();
    }

    public int calculateEnemySum() {
        return enemy.getDeckComponent().calculateHandSum();
    }

    public void resetWinner() {
        this.winner = null;
    }

    public void registerPlayerCardDraw(Card card) {
        this.lastCardDrawEvent = new CardDrawEventData(card.toString(), getPlayerName());
    }

    public void registerEnemyCardDraw(Card card) {
        this.lastCardDrawEvent = new CardDrawEventData(card.toString(), getEnemyName());
    }

    public void registerPlayerTurnWin(int damage) {
        createDamageEvent(getEnemyName(), damage);
        this.winner = player;
    }

    public void registerEnemyTurnWin(int damage) {
        createDamageEvent(getPlayerName(), damage);
        this.winner = enemy;
    }

    public void registerPlayerGameWin() {
        this.winner = player;
    }

    public void registerEnemyGameWin() {
        this.winner = enemy;
    }

    public void createDamageEvent(String targetName, int damage) {
        lastDamageEvent = new DamageEventData(targetName, damage);
    }

    // maybe find a better way to activate states and the other repetitive stuff
    private void transitionTo(State newState) {
        this.state = newState;
        this.state.handle(this);
    }

    public void activateStartRoundState() { 
        transitionTo(stateFactory.createStartRoundState());
    }

    public void activatePlayerTurnState() { 
        transitionTo(stateFactory.createPlayerTurnState()); 
    }

    public void activatePlayerOnlyTurnState() { 
        transitionTo(stateFactory.createPlayerOnlyState());
    }

    public void activateEnemyTurnState() { 
        transitionTo(stateFactory.createEnemyTurnState()); 
    }

    public void activateEnemyOnlyTurnState() { 
        transitionTo(stateFactory.createEnemyOnlyState()); 
    }

    public void activateEndTurnState() { 
        transitionTo(stateFactory.createEndTurnState());
    }

    public void activateEndGameState() { 
        transitionTo(stateFactory.createEndGameState());
    }

    public void roundOverConnect(Runnable runnable) { roundOver.connect(runnable); }
    public void playerTurnConnect(Runnable runnable) { playerTurn.connect(runnable); }
    public void combatOverConnect(Runnable runnable) { combatOver.connect(runnable); }
    public void takeDamageConnect(Runnable runnable) { takeDamage.connect(runnable); }
    public void enemyStandConnect(Runnable runnable) { enemyStand.connect(runnable); }
    public void drawCardConnect(Runnable runnable) { drawCard.connect(runnable); }

    public void emitRoundOver() { roundOver.emit(); }
    public void emitPlayerTurn() { playerTurn.emit(); }
    public void emitCombatOver() { combatOver.emit(); }
    public void emitTakeDamage() { takeDamage.emit(); }
    public void emitEnemyStand() { enemyStand.emit(); }
    public void emitDrawCard() { drawCard.emit(); }

    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }
    public String getPlayerName() { return player.getName(); }
    public String getEnemyName() { return enemy.getName(); }
    public int getPlayerCurrentHp() { return player.getHealthComponent().getCurrentHp(); }
    public int getEnemyCurrentHp() { return enemy.getHealthComponent().getCurrentHp(); }
    public List<Card> getPlayerCards() { return player.getDeckComponent().getCards(); }
    public List<Card> getEnemyCards() { return enemy.getDeckComponent().getCards(); }
    public CombatEntity getWinner() { return winner; }
    public DamageEventData getLastDamageEvent() { return lastDamageEvent; }
    public CardDrawEventData getLastDrawnCardEvent() { return lastCardDrawEvent; }
    public int getGlobalStand() { return globalStand; }
}
