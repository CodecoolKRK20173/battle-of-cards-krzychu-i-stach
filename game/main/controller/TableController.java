package main.controller;

import java.util.*;
import main.players.*;
import main.cards.*;


public class TableController {
    
    private List<Player> listOfPlayers = new ArrayList<Player>();
    private Deck deck;
  
    
    public void game() {
        this.deck = new Deck();
        createPlayers(4);
        dealCards();
        // for (int i = 0; i < deck.getDeck().size(); i++ ){
        //     System.out.println(deck.getDeck().get(i).getName());
        //     System.out.println(deck.getDeck().get(i).getFirstParameter());
        //     System.out.println(deck.getDeck().get(i).getSecondParameter());
        //     System.out.println(deck.getDeck().get(i).getThirdParameter());
        //     System.out.println(deck.getDeck().get(i).getFourthParameter());
        // }
        
        // cards printing
        
        // int num = 0;
        // for (Player player : this.listOfPlayers) {
        //     num ++;
        //     for (Card card : player.hand.getHandContent()){
        //         System.out.println("karta playera :" + num);
        //         System.out.println(card.getFirstParameter());
        //         System.out.println(card.getSecondParameter());
        //         System.out.println(card.getThirdParameter());
        //         System.out.println(card.getFourthParameter());
        //     }
        // }

        /// dealed car printing

        ComparatorOfCards comparator = new ComparatorOfCards();

        List<Card> listOfWinnerCard = comparator.getWinnerCardsList(this.listOfPlayers.get(0).getHand().getFirstCard(),
        this.listOfPlayers.get(1).getHand().getFirstCard(),this.listOfPlayers.get(2).getHand().getFirstCard()); 
        
        for (Card card : listOfWinnerCard) {
            System.out.println(card);
        }
        
        /// try to implement comparator


    }

    private void createPlayers(int numOfPlayers) {
        for (int i = 0; i < numOfPlayers; i ++) {
            listOfPlayers.add(new CPU());
        }
    }

    private void dealCards() {
        while (deckIsNotEmpty(this.deck)) {
            for (int i = 0; i < listOfPlayers.size(); i++) {
                listOfPlayers.get(i).getHand().getHandContent().add(this.deck.pickCard());
            }
        }
        
    } 

    private boolean deckIsNotEmpty(Deck deck) {
        if (deck.getDeck().size() > 0) {
            return true;
        }
        else 
            return false;
    }
}

