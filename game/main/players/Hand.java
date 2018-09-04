package main.players;

import java.util.*;
import main.cards.*;

public class Hand {

    private List<Card> cardsInHand;

    public Hand() {
        this.cardsInHand = new ArrayList<Card>();
    }

    public List<Card> getHandontent() {
        return this.cardsInHand;
    }

}