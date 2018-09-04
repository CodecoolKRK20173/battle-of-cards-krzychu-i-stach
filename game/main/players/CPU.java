package main.players;

public class CPU extends Player {

    public CPU() {
        this.hand = new Hand();
    }


    public CPU(String name) {
        this.name = name;
        this.hand = new Hand();
    }
}