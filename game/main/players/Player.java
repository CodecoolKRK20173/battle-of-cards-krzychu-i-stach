package main.players;

public abstract class Player {

    public abstract Hand getHand();

    public abstract String getName();
    
    public abstract int chooseParameter(Card topCard);

}