package blackjack.core.signal;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Lightweight event hub for delivering typed data to one or more listeners.
 *
 * <p>The signal stores callback references and forwards emitted values to each
 * connected consumer.</p>
 */
public class DataSignal<T> {
    private final Set<Consumer<T>> connections = new HashSet<>();

    public void connect(Consumer<T> listener) {
        connections.add(listener);
    }

    public void emit(T data) {
        for (Consumer<T> listener : connections) {
            listener.accept(data);
        }
    }

    public void clearConnections() {
        connections.clear();
    }
}