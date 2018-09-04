package main.players;

public class Human extends Player {
    
    private Hand hand;
    private String name;

    public Human(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

}