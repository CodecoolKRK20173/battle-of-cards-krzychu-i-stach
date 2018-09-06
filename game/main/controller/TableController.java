package main.controller;

import java.util.*;
import main.players.*;
import main.View;
import main.View.Message;
import main.cards.*;
import java.util.concurrent.TimeUnit;


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
  
    
    public TableController(View viewer) {
        this.deck = new Deck();
        this.cardsInGame = new ArrayList<Card>();
        this.viewer = viewer;
        this.listOfPlayers = new ArrayList<Player>();
        this.remainCards = new ArrayList<Card>();
        this.scan = new Scanner(System.in);
    }


    public void game() {
        createPlayers(3);
        dealCards();
        int choice = 0;
        boolean gameIsRunning = true;

        while(gameIsRunning) {
            viewer.printMainMenu();
            try {
              choice = scan.nextInt();
            } catch (InputMismatchException e) {
                scan.nextLine();
                continue;
            }
            switch(choice) {
                case 1: 
                    ComparatorOfCards comparator = new ComparatorOfCards();
                    setTrumper(0);
                    winFlag = false;
                    while(!winFlag) {
                        clearScreen();
                        turn(comparator);
                        clearScreen();
                    }
                    viewer.printWinner(getWinner());  // on/off print winner
                    // System.out.println("KOniec");
                    break;
                case 2:
                    System.out.println("Press button from 1 to 4 ;-D");
                    break;
                case 3:
                    gameIsRunning = false;
                    break;
            }
        }
    }

    private void createPlayers(int numOfPlayers) {
        int playerNumber = 1;
        // listOfPlayers.add(new Human("Stach"));  /// wl/wyl playera
        for (int i = 0; i < numOfPlayers; i ++) {
            String name = "BOT nr. " + String.valueOf(playerNumber);
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

    private void showPlayersCards() {
        for (Player player : listOfPlayers) {
            System.out.printf("%s has %d cards\n", player.getName(), player.getHand().getAmountOfCardsInHand());
        }
        System.out.println("");
    }

    private void showWhoIsTrumper() {
        for (Player player : listOfPlayers) {
            if (player.getTrumper())
                System.out.printf("%s CHOSES A PARAMETER\n\n", player.getName());
        }
    }

    private void turn(ComparatorOfCards comparator) {
        showPlayersCards();
        showWhoIsTrumper();
        delayPrintingBy(2);
        setKindOfTrumpAndCreateCardInGameList();
        this.listOfWinnerCards = comparator.getWinnerCardsList(this.cardsInGame);
        while(checkDraw()) {
            setPlayerWhihStillInGame(); 
            replaceCardsFromGameToRemainCards();
            listOfWinnerCards.clear();
            extraTimeBattle();
            this.listOfWinnerCards = comparator.getWinnerCardsList(this.cardsInGame);
        }
        replaceCardsFromGameToRemainCards();
        summaryResultOfBattle(); 
        setAllPlayerWhihStillInGameOnFalse();

    }

    private void setTrumper(int numOfPlayer) {
        this.listOfPlayers.get(numOfPlayer).setTrumperOnTrue();
    }

    private void delayPrintingBy(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    private void setKindOfTrumpAndCreateCardInGameList() {
        for (Player player : this.listOfPlayers) {
            if (player.getTrumper()) {
                System.out.printf("%15s CARD\n", player.getName().toUpperCase());
                player.setActuallyFirstCard(player.getHand().getFirstCard());
                player.removeFirstCardOfPlayer();
                viewer.printCard(player.getActuallyFirstCard());
                System.out.println("");
                this.kindOfTrump = player.chooseParameter(player.getActuallyFirstCard());
                delayPrintingBy(2);
                System.out.printf("%s chose parameter number %d, of value %d\n\n",player.getName(), kindOfTrump, player.getActuallyFirstCard().getParameter(kindOfTrump));
                this.cardsInGame.add(player.getActuallyFirstCard());
                delayPrintingBy(2);
            }
            else {
                if (player.getHand().getHandContent().size() > 0) {
                    System.out.printf("%15s CARD\n", player.getName().toUpperCase());
                    player.setActuallyFirstCard(player.getHand().getFirstCard());
                    viewer.printCard(player.getActuallyFirstCard());
                    System.out.println("");
                    player.removeFirstCardOfPlayer();
                    this.cardsInGame.add(player.getActuallyFirstCard());
                    delayPrintingBy(2);
                }
            }
        }
        delayPrintingBy(2);
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
                try {
                player.setActuallyFirstCard(player.getHand().getFirstCard());
                } 
                catch(IndexOutOfBoundsException e) {
                    viewer.printWinner(getWinner()); // bandycki numer
                }
                try {
                    player.removeFirstCardOfPlayer();
                }
                catch(IndexOutOfBoundsException e) {
                    viewer.printWinner(getWinner()); // bandycki numer
                }
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
                System.out.printf("%s WINS THE ROUND", player.getName().toUpperCase());
                delayPrintingBy(5);
                for (Card card : remainCards) {
                    player.getHand().getHandContent().add(card);
                }
                remainCards.clear();
                this.winFlag = checkWinner();
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
