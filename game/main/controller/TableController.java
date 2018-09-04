package main.controller;

import java.util.*;
import main.players.*;
import main.cards.*;


public class TableController {
    
    private List<Player> listOfPlayers = new ArrayList<Player>();
    private Deck deck;

    private int kindOfTrump;
    private Card[] cardInGame;
    private List<Card> listOfWinnerCards;
  
    
    public void game() {
        this.deck = new Deck();
        createPlayers(4);
        this.cardInGame = new Card[4];
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
        //     for (Card card : player.getHand().getHandContent()){
        //         System.out.println("karta playera :" + num);
        //         System.out.println(card.getFirstParameter());
        //         System.out.println(card.getSecondParameter());
        //         System.out.println(card.getThirdParameter());
        //         System.out.println(card.getFourthParameter());
        //     }
        // }

        /// dealed car printing

       
        
        
        
        ComparatorOfCards comparator = new ComparatorOfCards();
        setTrumper(1);
        turn(comparator);
        
        for (Card card : this.listOfWinnerCards) {
            System.out.println(card.getName());
        }

        //try to impement turn

        int num = 0;
        for (Player player : this.listOfPlayers) {
                num ++;
                System.out.println("karta playera :" + num);
                System.out.println(player.getHand().getFirstCard().getTrump() + " traf sila");
            

                }
            
        /// print first card of all players
        

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

    private void turn(ComparatorOfCards comparator) {
        setKindOfTrump();
        setArrayOfCardsToCompare();
        this.listOfWinnerCards = comparator.getWinnerCardsList(this.cardInGame);


    }

    private void setTrumper(int numOfPlayer) {
        this.listOfPlayers.get(numOfPlayer - 1).setTrumperOnTrue();
    }

    private void setKindOfTrump() {
        for (Player player : this.listOfPlayers) {
            if (player.getTrumper()) {
                Card topCard = player.getHand().getFirstCard();
                this.kindOfTrump = player.chooseParameter(topCard);
                this.cardInGame[0] = topCard;
            }
        }
    }

    private void setArrayOfCardsToCompare() {
        int indexOfCardInGame = 1;
        for (Player player : listOfPlayers) {
            if (!player.getTrumper()) {
                this.cardInGame[indexOfCardInGame] = player.getHand().getFirstCard();
                indexOfCardInGame ++;
            }
        }
        setTrumpInCards();
    }

    private void setTrumpInCards() {
        for (Card card : cardInGame) {
            card.setTrump(card.getParameter(this.kindOfTrump));
        }
    }
}

