package blackjack;

import blackjack.engine.GameInitializer;

/**
 * Application entry point for the Blackjack rogue-like game.
 * <p>
 * This class boots the system and delegates initialization to
 * {@link GameInitializer}.
 * </p>
 */
public class Main {
    /**
     * Launches the game with the default startup sequence.
     *
     * @param args command-line arguments, currently ignored by the game
     */
    public static void main(String[] args) {
        GameInitializer initializer = new GameInitializer();
        initializer.startNewGame();
    }
}