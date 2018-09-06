package main.players;

import main.cards.*;

public abstract class Player {

    boolean trumper;
    boolean stillInGame;
    Card actuallyFirstCard;

    public abstract Hand getHand();

    public abstract String getName();
    
    public abstract int chooseParameter(Card topCard);

    public abstract boolean getTrumper();

    public abstract void setTrumperOnTrue();

    public abstract void setTrumperOnFalse();

    public abstract boolean getStillInGame();

    public abstract void setStillInGame(boolean type);

    public void setActuallyFirstCard(Card card) {
        this.actuallyFirstCard = card;
    }

    public Card getActuallyFirstCard() {
        return this.actuallyFirstCard;
    }

    public void removeFirstCardOfPlayer() {
        this.getHand().getHandContent().remove(0);
    }

}