package main.players;

import java.util.*;
import main.cards.*;

public class Hand {

    private List<Card> cardsInHand;

    public Hand() {
        this.cardsInHand = new ArrayList<Card>();
    }

    public List<Card> getHandContent() {
        return this.cardsInHand;
    }

    public Card getFirstCard() {
        return this.cardsInHand.get(0);
    }

    public int amountOfCardsInHand() {
        return cardsInHand.size();
    }
}
