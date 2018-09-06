package main.controller;

import java.util.*;
import main.players.*;
import main.View;
import main.cards.*;


public class TableController {
    
    private List<Player> listOfPlayers = new ArrayList<Player>();
    private Deck deck;
    private int kindOfTrump;
    private List<Card> cardInGame;
    private List<Card> listOfWinnerCards;
    private List<Card> remainCards = new ArrayList<Card>();
    private boolean winFlag;
    private View viewer;
  
    
    public void game() {
        this.deck = new Deck();
        createPlayers(1);
        this.cardInGame = new ArrayList<Card>();
        dealCards();
        this.viewer = new View();
        viewer.printMainMenu();
        Scanner scan = new Scanner(System.in);
        int chooice = scan.nextInt();
        switch(chooice) {
            case(1): 
            ComparatorOfCards comparator = new ComparatorOfCards();
            setTrumper(0);
            winFlag = false;

            while(!winFlag) {

                turn(comparator);
            }
            getWinner();
            break;


            case(2):

            case(3):
        }
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
 
        
        
    }

    private void createPlayers(int numOfPlayers) {
        int playerNumber = 1;
        for (int i = 0; i < numOfPlayers; i ++) {
            String name = "Player " + String.valueOf(playerNumber);
            listOfPlayers.add(new CPU(name));
            playerNumber++;
        }
        listOfPlayers.add(new Human("Stach"));
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
        // printView(); // help method
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
                // printView();  // help method
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
                if (player instanceof Human) {
                    viewer.printCard(topCard);
                }
                this.kindOfTrump = player.chooseParameter(topCard);
                this.cardInGame.add(topCard);
            }
            else {
                if (player.getHand().getHandContent().size() > 0) {
                    this.cardInGame.add(player.getHand().getFirstCard());
                }
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
        try {
            for (Player player : this.listOfPlayers) {
                player.getHand().getHandContent().remove(0);
            }
        }
        catch(IndexOutOfBoundsException io) {
            
        }
    }

    private void removeUsedCardsFromPlayersInExtraTime() {
        for (Player player : this.listOfPlayers) {
            if (player.getStillInGame()) {
                try {
                    player.getHand().getHandContent().remove(0);
                }
                catch(IndexOutOfBoundsException io) {

                }
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
            try {
                if (listOfWinnerCards.contains(player.getHand().getFirstCard())){
                    player.setStillInGame(true);
                }
                else player.setStillInGame(false);
            }
            catch(IndexOutOfBoundsException ioobe) {
                
            }
            
        }
    }


    private void extraTimeBattle() {
        System.out.println("Weszlo do extratime");
        checkWinnerInExtraTime();
        
            for (Player player : listOfPlayers) {
                try {
                    if (player.getStillInGame()) {
                        this.cardInGame.add(player.getHand().getFirstCard());
                        if (player instanceof Human && player.getTrumper()) {
                            viewer.printCard(player.getHand().getFirstCard());
                        }
                    }
                }
            
            catch(IndexOutOfBoundsException iobe) {
                
                break;
                
            }
            setTrumpInCards();
        }
    }
        
    


    private void checkWinnerInExtraTime() {
        if(checkWinner()) {
            winFlag = true;
        }
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



    // private void printView()  {  /// temporary method for view!
    //     for (Card card : this.listOfWinnerCards) {
    //         System.out.println(card.getName() + " zwycieska karta");
    //     }

    //     //print winner card

    //     int num = 0;
    //     for (Player player : this.listOfPlayers) {
    //             num ++;
    //             System.out.println("karta playera :" + num);
    //             System.out.println(player.getHand().getFirstCard().getName() + "imie");
    //             System.out.println(player.getHand().getFirstCard().getTrump() + " traf sila");
            

    //             }
            
    //     /// print first card of all players
    // }


}
