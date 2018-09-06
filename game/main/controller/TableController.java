package main.controller;

import java.util.*;
import main.players.*;
import main.View;
import main.View.Message;
import main.cards.*;


public class TableController {
    
    private List<Player> listOfPlayers;
    private Deck deck;
    private int kindOfTrump;
    private List<Card> cardsInGame;
    private List<Card> listOfWinnerCards;
    private List<Card> remainCards;
    private boolean winFlag;
    private View viewer;
    private Scanner scan;
  
    
    public TableController() {
        this.deck = new Deck();
        this.cardsInGame = new ArrayList<Card>();
        this.viewer = new View();
        this.listOfPlayers = new ArrayList<Player>();
        this.remainCards = new ArrayList<Card>();
        this.scan = new Scanner(System.in);
    }


    public void game() {
        createPlayers(2);
        dealCards();
        viewer.printMainMenu();
        int chooice = scan.nextInt();
        switch(chooice) {
            case(1): 
                ComparatorOfCards comparator = new ComparatorOfCards();
                setTrumper(0);
                winFlag = false;
                while(!winFlag) {
                    turn(comparator);
                }
                viewer.printWinner(getWinner());
                break;
            case(2):
                System.out.println("Press button from 1 to 4 ;-D");
                break;
            case(3):
                break;
        }
    }

    private void createPlayers(int numOfPlayers) {
        int playerNumber = 1;
        for (int i = 0; i < numOfPlayers; i ++) {
            String name = "Player " + String.valueOf(playerNumber);
            listOfPlayers.add(new CPU(name));
            playerNumber++;
        }
        // listOfPlayers.add(new Human("Stach"));
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
        this.listOfWinnerCards = comparator.getWinnerCardsList(this.cardsInGame);
        
        if (checkDraw()) {

            while(checkDraw()) {
                setPlayerWhihStillInGame(); 
                replaceCardsFromGameToRemainCards();
                listOfWinnerCards.clear();
                extraTimeBattle();
                this.listOfWinnerCards = comparator.getWinnerCardsList(this.cardsInGame);
            }
        }

        else {
            replaceCardsFromGameToRemainCards();
            summaryResultOfBattle(); 
            setAllPlayerWhihStillInGameOnFalse();
        }
    }

    private void setTrumper(int numOfPlayer) {
        this.listOfPlayers.get(numOfPlayer).setTrumperOnTrue();
    }

    private void setKindOfTrumpAndCreateCardInGameList() {
        for (Player player : this.listOfPlayers) {
            if (player.getTrumper()) {
                player.setActuallyFirstCard(player.getHand().getFirstCard());
                player.removeFirstCardOfPlayer();
                if (player instanceof Human) {
                    viewer.printCard(player.getActuallyFirstCard());
                }
                this.kindOfTrump = player.chooseParameter(player.getActuallyFirstCard());
                this.cardsInGame.add(player.getActuallyFirstCard());
            }
            else {
                if (player.getHand().getHandContent().size() > 0) {
                    player.setActuallyFirstCard(player.getHand().getFirstCard());
                    player.removeFirstCardOfPlayer();
                    this.cardsInGame.add(player.getActuallyFirstCard());
                }
            }
        }
        setTrumpInCards();
    }

    private void setTrumpInCards() {
        for (Card card : cardsInGame) {
            card.setTrump(card.getParameter(this.kindOfTrump));
        }
    }

    private boolean checkDraw() {
        if (listOfWinnerCards.size() > 1) {
            return true;
        }
        else return false;
    } 

    private void replaceCardsFromGameToRemainCards() {
        
        Iterator<Card> iter = cardsInGame.iterator();
        while(iter.hasNext()) {
            remainCards.add(iter.next());
            iter.remove();
        }
    }

    private void setPlayerWhihStillInGame() {

        for (Player player : listOfPlayers) {
            try {
                if (listOfWinnerCards.contains(player.getActuallyFirstCard())) {
                    player.setStillInGame(true);
                }
                else player.setStillInGame(false);
            }
            catch(IndexOutOfBoundsException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void extraTimeBattle() {
        viewer.printMessage(Message.DRAW);
        checkWinnerInExtraTime();
        
            for (Player player : listOfPlayers) {
                player.setActuallyFirstCard(player.getHand().getFirstCard());
                player.removeFirstCardOfPlayer();
                Card playerFirstCard = player.getActuallyFirstCard();
                try {
                    if (player.getStillInGame()) {
                        this.cardsInGame.add(playerFirstCard);
                        if (player instanceof Human && player.getTrumper()) {
                            
                            viewer.printCard(playerFirstCard);
                            this.kindOfTrump = player.chooseParameter(playerFirstCard);
                            setTrumpInCards();
                        }
                    }
                }
            
            catch(IndexOutOfBoundsException iobe) {
                iobe.printStackTrace();
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
            if (listOfWinnerCards.contains(player.getActuallyFirstCard())) {
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

    private Player getWinner() {
        int numberOfWinnerCards = 0;
        Player playerWon = null; 
        for (Player player : this.listOfPlayers) {
            if (player.getHand().getHandContent().size() > numberOfWinnerCards) {
                numberOfWinnerCards = player.getHand().getHandContent().size();
            }
        }
        for (Player player : this.listOfPlayers) {
            if (player.getHand().getHandContent().size() == numberOfWinnerCards) {
                playerWon = player;
            }
        }
        return playerWon;
    }
}
