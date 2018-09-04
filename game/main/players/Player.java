package main.players;

import main.cards.*;

public abstract class Player {

    boolean trumper;

    public abstract Hand getHand();

    public abstract String getName();
    
    public abstract int chooseParameter(Card topCard);

    public abstract boolean getTrumper();

    public abstract void setTrumperOnTrue();

}