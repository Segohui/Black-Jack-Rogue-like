package blackjack.controller;

import java.util.List;
import java.util.function.Consumer;

import blackjack.core.battle.BattleCore;
import blackjack.core.inventory.Inventory;
import blackjack.dtos.core.battle.CardDrawEventDTO;
import blackjack.dtos.core.battle.CombatOverDTO;
import blackjack.dtos.core.battle.DamageEventDTO;
import blackjack.dtos.core.battle.EntityStateDTO;
import blackjack.dtos.core.items.ItemInfoDTO;
import blackjack.dtos.entity.AIRecordDTO;

/**
 * Controller that exposes battle-related actions and state to the view layer.
 *
 * <p>This class is responsible for translating UI intents into domain operations
 * on {@link BattleCore} and for exposing DTO data needed by the terminal views.
 *</p>
 */
public class BattleController {
    private final BattleCore core;
    private final Inventory playerInventory;

    /**
     * Creates a battle controller with the core battle engine and player inventory.
     *
     * @param core battle engine used to execute combat actions
     * @param playerInventory inventory used to manage player items
     */
    public BattleController(BattleCore core, Inventory playerInventory) {
        this.core = core;
        this.playerInventory = playerInventory;
        playerInventory.clearItemTriggeredConnections();
    }

    /**
     * Begins the battle sequence in the battle core.
     */
    public void startBattle() {
        core.startCombat();
    }

    /**
     * Prepares a new combat encounter using the supplied enemy record.
     *
     * @param aiRecord enemy record containing the next enemy data
     */
    public void prepareBattleStage(AIRecordDTO aiRecord) {
        core.prepareForNewBattle(aiRecord);
    }

    /**
     * Retrieves the current enemy state for presentation.
     *
     * @return enemy state DTO
     */
    public EntityStateDTO getEnemyData() {
        return core.getEnemyData();
    }

    /**
     * Retrieves the current player state for presentation.
     *
     * @return player state DTO
     */
    public EntityStateDTO getPlayerData() {
        return core.getPlayerData();
    }

    /**
     * Retrieves state data for the specified entity name.
     *
     * @param name name of the entity whose state is requested
     * @return entity state DTO
     */
    public EntityStateDTO getEntityDataByName(String name) {
        return core.getEntityStateDataByName(name);
    }

    /**
     * Requests a player hit action from the battle core.
     */
    public void playerHit() {
        core.playerHit();
    }

    /**
     * Requests a player stand action from the battle core.
     */
    public void playerStand() {
        core.playerStand();
    }

    /**
     * Returns whether the player has any usable items.
     *
     * @return true when the player inventory contains items
     */
    public boolean playerHasItems() {
        return playerInventory.hasItems();
    }

    /**
     * Requests the use of an item by index.
     *
     * @param idx index of the item to use
     */
    public void playerUseItem(int idx) {
        core.playerUseItem(idx);
    }

    /**
     * Returns the current list of player item DTOs.
     *
     * @return item information DTO list
     */
    public List<ItemInfoDTO> getItemInfos() {
        return playerInventory.getItemInfos();
    }

    // Connects

    /**
     * Connects a listener to the round-over event from the battle core.
     *
     * @param listener callback invoked when a round ends
     */
    public void roundOverConnect(Consumer<String> listener) {
        core.roundOverConnect(listener);
    }

    /**
     * Connects a listener to the player turn event.
     *
     * @param runnable callback invoked when the player's turn begins
     */
    public void playerTurnConnect(Runnable runnable) {
        core.playerTurnConnect(runnable);
    }

    /**
     * Connects a listener to damage events for both player and enemy.
     *
     * @param listener callback invoked when any combatant takes damage
     */
    public void takeDamageConnect(Consumer<DamageEventDTO> listener) {
        core.playerTakeDamageConnect(listener);
        core.enemyTakeDamageConnect(listener);
    }

    /**
     * Connects a listener to stand events for both player and enemy.
     *
     * @param listener callback invoked when an entity stands
     */
    public void entityStandConnect(Consumer<String> listener) {
        core.playerStandConnect(listener);
        core.enemyStandConnect(listener);
    }

    /**
     * Connects a listener to draw card events for both player and enemy.
     *
     * @param listener callback invoked when a card is drawn
     */
    public void drawCardConnect(Consumer<CardDrawEventDTO> listener) {
        core.drawCardPlayerConnect(listener);
        core.drawCardEnemyConnect(listener);
    }

    /**
     * Connects a listener to combat-over events.
     *
     * @param listener callback invoked when combat ends
     */
    public void combatOverConnect(Consumer<CombatOverDTO> listener) {
        core.combatOverConnect(listener);
    }

    /**
     * Connects a listener to game-over events.
     *
     * @param listener callback invoked with whether the player survived
     */
    public void gameOverConnect(Consumer<Boolean> listener) {
        core.gameOverConnect(listener);
    }

    /**
     * Connects a listener for item-triggered UI updates.
     *
     * @param listener callback invoked when an item effect occurs
     */
    public void itemTriggeredConnect(Consumer<ItemInfoDTO> listener) {
        playerInventory.itemTriggeredConnect(listener);
    }
}
