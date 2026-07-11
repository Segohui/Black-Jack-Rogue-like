package blackjack.core.battle;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.DataSignal;
import blackjack.core.EmptySignal;
import blackjack.core.battle.states.State;
import blackjack.core.battle.states.StateFactory;
import blackjack.core.cards.Card;
import blackjack.dto.CardDrawEventData;
import blackjack.dto.DamageEventData;
import blackjack.entity.AIRecord;
import blackjack.entity.Behavior;
import blackjack.entity.Entity;

public class BattleCore {
    private final EmptySignal playerTurn = new EmptySignal();
    private final EmptySignal roundOver = new EmptySignal();
    private final EmptySignal takeDamage = new EmptySignal();
    private final EmptySignal enemyStand = new EmptySignal();
    private final EmptySignal drawCard = new EmptySignal();

    private final DataSignal<Boolean> combatOver = new DataSignal<>();

    private final Entity player;
    private int globalStand = 21; // may change with power ups
    private int lastGoldReward = 0;

    private Entity enemy;
    private Behavior enemyBehavior;
    private StateFactory stateFactory;
    private Entity winner;
    private DamageEventData lastDamageEvent;
    private CardDrawEventData lastCardDrawEvent;
    private State state;

    public BattleCore(Entity player) {
        this.player = player;
    }

    public void startCombat(AIRecord enemyRecord) {
        resetCore();
        this.enemy = enemyRecord.entity();
        this.enemyBehavior = enemyRecord.behavior();
        this.stateFactory = new StateFactory(player, enemy, enemyBehavior);
        activateStartRoundState();
    }

    private void resetCore() {
        this.lastDamageEvent = null;
        this.lastCardDrawEvent = null;
        this.state = null;
        this.winner = null;
        this.enemy = null;
        this.enemyBehavior = null;
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
        return player.calculateHandSum();
    }

    public int calculateEnemySum() {
        return enemy.calculateHandSum();
    }

    public void resetWinner() {
        this.winner = null;
    }

    public void registerCardDraw(Card card, Entity entity) {
        this.lastCardDrawEvent = new CardDrawEventData(card.toString(), entity.getName());
    }

    public void registerAttack(Entity winner, Entity loser, int damage) {
        this.lastDamageEvent = new DamageEventData(loser.getName(), damage);
        this.winner = winner;
    }

    public void registerBattleWinner(Entity winner) {
        this.winner = winner;
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

    public void registerGoldReward(int amount){
        this.lastGoldReward = amount;
    }

    public int getLastGoldReward(){
        return lastGoldReward;
    }

    public void roundOverConnect(Runnable runnable) { roundOver.connect(runnable); }
    public void playerTurnConnect(Runnable runnable) { playerTurn.connect(runnable); }
    public void takeDamageConnect(Runnable runnable) { takeDamage.connect(runnable); }
    public void enemyStandConnect(Runnable runnable) { enemyStand.connect(runnable); }
    public void drawCardConnect(Runnable runnable) { drawCard.connect(runnable); }
    
    public void combatOverConnect(Consumer<Boolean> listener) { combatOver.connect(listener); }

    public void emitRoundOver() { roundOver.emit(); }
    public void emitPlayerTurn() { playerTurn.emit(); }
    public void emitTakeDamage() { takeDamage.emit(); }
    public void emitEnemyStand() { enemyStand.emit(); }
    public void emitDrawCard() { drawCard.emit(); }

    public void emitCombatOver(boolean isPlayerAlive) { combatOver.emit(isPlayerAlive); }

    public String getPlayerName() { return player.getName(); }
    public String getEnemyName() { return enemy.getName(); }
    public int getPlayerCurrentHp() { return player.getCurrentHp(); }
    public int getEnemyCurrentHp() { return enemy.getCurrentHp(); }
    public List<Card> getPlayerCards() { return player.getCards(); }
    public List<Card> getEnemyCards() { return enemy.getCards(); }
    public Entity getWinner() { return winner; }
    public DamageEventData getLastDamageEvent() { return lastDamageEvent; }
    public CardDrawEventData getLastDrawnCardEvent() { return lastCardDrawEvent; }
    public int getGlobalStand() { return globalStand; }
    public Entity getPlayer() { return player; }
}
