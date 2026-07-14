package blackjack.core.signal;

import java.util.HashSet;
import java.util.Set;

/**
 * Lightweight event hub used for notifications without payload data.
 *
 * <p>Listeners are invoked whenever the signal is emitted.</p>
 */
public class EmptySignal {
    private final Set<Runnable> connections = new HashSet<>();

    public void connect(Runnable runnable) {
        connections.add(runnable);
    }

    public void emit() {
        for (Runnable runnable : connections) {
            runnable.run();
        }
    }

    public void clearConnections() {
        connections.clear();
    }
} 
