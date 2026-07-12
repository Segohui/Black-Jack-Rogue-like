package blackjack.core.shop;

public interface Buyable {
    String getName();
    String getDescription();
    int getCost();
    void buy();
}