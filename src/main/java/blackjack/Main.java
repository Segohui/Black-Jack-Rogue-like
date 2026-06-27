package blackjack;

import blackjack.visual.InputOutput;
import blackjack.visual.terminal.TerminalBlackjack;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
        TerminalBlackjack t = new TerminalBlackjack(io);
        t.startRound();
    }
}