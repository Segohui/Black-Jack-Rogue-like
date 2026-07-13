package blackjack;

import blackjack.engine.GameInitializer;

public class Main {
    public static void main(String[] args) {
        GameInitializer initializer = new GameInitializer();
        initializer.startNewGame();
    }
}