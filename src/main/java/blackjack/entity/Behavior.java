package blackjack.entity;

public interface Behavior {
    void playTurn(int globalStand);
    boolean hasStopped(int globalStand);
}
