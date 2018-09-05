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

    
    public List<Card> getWinnerCardsList(Card[] cards) {
        List<Card> winnerList = new ArrayList<Card>();
        List<Card> sortedCards = convertArrayOfCardsForSortedList(cards);
        Card highestCard = sortedCards.get(sortedCards.size()-1);
    
        for (Card card : sortedCards) {
            if (this.compare(card, highestCard) == 0) {
                winnerList.add(card);
            }
        }
        return winnerList;
    }


    private List<Card> convertArrayOfCardsForSortedList(Card[] cards) {
        List<Card> sortedCards = new ArrayList<Card>();
        for (Card card : cards) {
            sortedCards.add(card);
        }
        Collections.sort(sortedCards, this);
        return sortedCards;
    }
}
    
        
    

 
