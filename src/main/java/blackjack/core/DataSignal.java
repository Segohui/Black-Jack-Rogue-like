package blackjack.core;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

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
}