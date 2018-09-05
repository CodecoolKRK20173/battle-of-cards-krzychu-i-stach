package main.controller;

import java.util.*;
import main.players.*;
import main.cards.*;


public class TableController {
    
    private List<Player> listOfPlayers = new ArrayList<Player>();
    private Deck deck;

    private int kindOfTrump;
    private List<Card> cardInGame;
    private List<Card> listOfWinnerCards;
    private List<Card> remainCards = new ArrayList<Card>();
  
    
    public void game() {
        this.deck = new Deck();
        createPlayers(4);
        this.cardInGame = new ArrayList<Card>();
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
        setTrumper(0);

        while(!checkWinner()) {
            turn(comparator);
        }
        getWinner();
    }

    private void createPlayers(int numOfPlayers) {
        int playerNumber = 1;
        for (int i = 0; i < numOfPlayers; i ++) {
            String name = "Player " + String.valueOf(playerNumber);
            listOfPlayers.add(new CPU(name));
            playerNumber++;
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
        setKindOfTrumpAndCreateCardInGameList();
        this.listOfWinnerCards = comparator.getWinnerCardsList(this.cardInGame);
        printView(); // help method
        int levelOfExtraTime = 1;
        
        if (checkDraw()) {

            while(checkDraw()) {
            setPlayerWhihStillInGame();
            
            replaceCardsFromGameToRemainCards();

            if (levelOfExtraTime <= 1) {
                removeUsedCardsFromPlayers();
            }
            else {
                removeUsedCardsFromPlayersInExtraTime();
            }
            listOfWinnerCards.clear();

            extraTimeBattle();
            this.listOfWinnerCards = comparator.getWinnerCardsList(this.cardInGame);
            levelOfExtraTime ++;
            printView();  // help method
            }
        }

        else {
            replaceCardsFromGameToRemainCards();
            summaryResultOfBattle();

            if (levelOfExtraTime <= 1) {
                removeUsedCardsFromPlayers();
            }
            else {
                removeUsedCardsFromPlayersInExtraTime();
            }
            setAllPlayerWhihStillInGameOnFalse();
            
        }
       
    }


    private void setTrumper(int numOfPlayer) {
        this.listOfPlayers.get(numOfPlayer).setTrumperOnTrue();
    }


    private void setKindOfTrumpAndCreateCardInGameList() {
        for (Player player : this.listOfPlayers) {
            if (player.getTrumper()) {
                Card topCard = player.getHand().getFirstCard();
                this.kindOfTrump = player.chooseParameter(topCard);
                this.cardInGame.add(topCard);
            }
            else {
                this.cardInGame.add(player.getHand().getFirstCard());
            }
        }
        setTrumpInCards();
    }



    private void setTrumpInCards() {
        for (Card card : cardInGame) {
            card.setTrump(card.getParameter(this.kindOfTrump));
        }
    }


    private boolean checkDraw() {
        if (listOfWinnerCards.size() > 1) {
            return true;
        }
        else return false;

    } 


    private void removeUsedCardsFromPlayers() {
        for (Player player : this.listOfPlayers) {
            player.getHand().getHandContent().remove(0);
        }
    }

    private void removeUsedCardsFromPlayersInExtraTime() {
        for (Player player : this.listOfPlayers) {
            if (player.getStillInGame()) {
                player.getHand().getHandContent().remove(0);
            }
        }
    }


    private void replaceCardsFromGameToRemainCards() {
        
        Iterator<Card> iter = cardInGame.iterator();
        while(iter.hasNext()) {
            remainCards.add(iter.next());
            iter.remove();
        }
    }


    private void setPlayerWhihStillInGame() {

        for (Player player : listOfPlayers) {
            if (listOfWinnerCards.contains(player.getHand().getFirstCard())){
                player.setStillInGame(true);
            }
            else player.setStillInGame(false);
        }
    }


    private void extraTimeBattle() {
        System.out.println("Weszlo do extratime");
        for (Player player : listOfPlayers) {
            if (player.getStillInGame()) {
                this.cardInGame.add(player.getHand().getFirstCard());
            }
        }
        setTrumpInCards();
    }


    private void setAllPlayerWhihStillInGameOnFalse() {
        for (Player player : listOfPlayers) {
            player.setStillInGame(false);
        }
    }
    

    private void summaryResultOfBattle() {
        for (Player player : this.listOfPlayers) {
            if (listOfWinnerCards.contains(player.getHand().getFirstCard())) {
                setAllPlayersTrumperOnFalse();
                player.setTrumperOnTrue();
                
                for (Card card : remainCards) {
                    player.getHand().getHandContent().add(card);
                }
            }
        }
    }

    
    private void setAllPlayersTrumperOnFalse() {
        for (Player player: this.listOfPlayers) {
            player.setTrumperOnFalse();
        }
    }


    private boolean checkWinner() {
        boolean gameStatus = false;
        for (Player player : this.listOfPlayers) {
            if (player.getHand().getHandContent().size() == 0) {
                gameStatus = true;
            }
        }
        return gameStatus;
    }

    private void getWinner() {
        int numberOfWinnerCards = 0;
        for (Player player : this.listOfPlayers) {
            if (player.getHand().getHandContent().size() > numberOfWinnerCards) {
                numberOfWinnerCards = player.getHand().getHandContent().size();
            }
        }
        for (Player player : this.listOfPlayers) {
            if (player.getHand().getHandContent().size() == numberOfWinnerCards) {
                System.out.println(player.getName() + " is winner!!!");;
            }
        }
    }



    private void printView()  {  /// temporary method for view!
        for (Card card : this.listOfWinnerCards) {
            System.out.println(card.getName() + " zwycieska karta");
        }

        //print winner card

        int num = 0;
        for (Player player : this.listOfPlayers) {
                num ++;
                System.out.println("karta playera :" + num);
                System.out.println(player.getHand().getFirstCard().getName() + "imie");
                System.out.println(player.getHand().getFirstCard().getTrump() + " traf sila");
            

                }
            
        /// print first card of all players
    }


}

