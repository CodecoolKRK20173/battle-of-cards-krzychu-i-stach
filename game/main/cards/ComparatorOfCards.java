package main.cards;


import main.cards.*;
import java.util.*;



public class ComparatorOfCards implements Comparator {


    public int compare(Object cardToCompare, Object maxValue) {
        Card card = (Card)cardToCompare;
        int maxValueOfParameter = (Integer)maxValue;
        if (card.getTrump() == maxValueOfParameter) {
            return 0;
        }
        else if (card.getTrump() < maxValueOfParameter) {
            return -1;
        }
        else return 1;
    }

    
    public List<Card> getWinnerCardsList(Card ... cards) {
        List<Card> winnerList = new ArrayList<Card>();
        int maxValueOfParameter = 0;
        for (Card card : cards) {
            if (card.getTrump() > maxValueOfParameter)
                maxValueOfParameter = card.getTrump();
        }
        for (Card card : cards) {
            if (this.compare(card, maxValueOfParameter) == 0)
                winnerList.add(card);
        }
        return winnerList;
    }

 
}