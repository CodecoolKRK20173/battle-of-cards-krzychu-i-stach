package main.players;

import java.util.InputMismatchException;
import java.util.Scanner;
import main.cards.*;

public class Human extends Player {
    
    private Hand hand;
    private String name;

    public Human(String name) {
        this.name = name;
        this.hand = new Hand();
        this.trumper = false;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int chooseParameter(Card topCard) {
        boolean choosingParameter = true;
        Scanner scanner = new Scanner(System.in);
        int userOption = 0;
        
        while (choosingParameter) {
            try {
                userOption = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }
            if (userOption > 4 || userOption <1) {
                scanner.nextLine();
                continue;
            }
        }  
        return userOption;   
    }

    public void setTrumperOnTrue() {
        this.trumper = true;
    }

    public boolean getTrumper() {
        return this.trumper;
    }
}
