package main.players;

public class CPU extends Player {

    private Hand hand;
    private String name;

    public CPU() {
        this.hand = new Hand();
    }

    public CPU(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }
}   