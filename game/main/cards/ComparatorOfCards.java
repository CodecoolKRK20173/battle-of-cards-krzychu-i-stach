package main.cards;

import main.cards.*;
import java.util.*;

public class ComparatorOfCards implements Comparator<Card> {


    public int compare(Card card1, Card card2) {
        
        if (card1.getTrump() == card2.getTrump()) {
            return 0;
        }
        else if (card1.getTrump() < card2.getTrump()) {
            return -1;
        }
        else return 1;
    }

    
    public List<Card> getWinnerCardsList(List<Card> cards) {
        List<Card> winnerList = new ArrayList<Card>();
        Collections.sort(cards, this);
        if (cards.size() > 0) {
            Card highestCard = cards.get(cards.size()-1);
        
            for (Card card : cards) {
                if (this.compare(card, highestCard) == 0) {
                    winnerList.add(card);
                }
            }
        }
        return winnerList;
    }
}
