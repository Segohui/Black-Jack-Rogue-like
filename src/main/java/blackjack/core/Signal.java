package blackjack.core;

import java.util.HashSet;
import java.util.Set;

public class Signal {
    private final Set<Runnable> connections = new HashSet<>();

    public void connect(Runnable runnable) {
        connections.add(runnable);
    }

    public void emit() {
        for (Runnable runnable : connections) {
            runnable.run();
        }
    }
}
