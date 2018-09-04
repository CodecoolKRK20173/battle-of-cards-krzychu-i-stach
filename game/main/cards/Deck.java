package main.cards;

import java.util.*;

public class Deck {

    private List<Card> listOfCards = new ArrayList<Card>();
    
    public Deck() {
        this.listOfCards = createCardsDeck();
        shuffle();
    }

    private List<Card> createCardsDeck() {
        List<Card> cards = new ArrayList<Card>();
        for (CardType type : CardType.values()) {
            for (int i = 0; i < 12; i++) { 
                cards.add(new Card(type));
            }
        }
        return cards;
    }

    public List<Card> getDeck() {
        return this.listOfCards;
    }

    public Card pickCard() {
        Card temp = this.listOfCards.get(0);
        this.listOfCards.remove(0);
        return temp;
       }

    private void shuffle() {
        ArrayList<Integer> listOfIndex = createListOfRandomIndex();
        List<Card> shuffleDeck = new ArrayList<Card>();
        for (int index : listOfIndex) {
            shuffleDeck.add(this.listOfCards.get(index));
        }
        this.listOfCards.clear();
        this.listOfCards = shuffleDeck;
    }

    private ArrayList<Integer> createListOfRandomIndex() {
        ArrayList<Integer> listOfIndex = new ArrayList<Integer>();
        Random rand = new Random();
        boolean flag = true;
        while(flag) {
            int randomNum = rand.nextInt(48);
            if (!listOfIndex.contains(randomNum)) {
                listOfIndex.add(randomNum);
            }
            if (listOfIndex.size() == 48) {
                flag = false;
            }
        }
        return listOfIndex;    
    }
}