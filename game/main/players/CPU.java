package main.players;

import java.util.Random;
import main.cards.*;

public class CPU extends Player {

    private Hand hand;
    private String name;

    public CPU() {
        this.hand = new Hand();
        this.trumper = false;
    }

    public CPU(String name) {
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
        int[] cardParametersValues = topCard.getCardParameters();
        int highestValue = 0;
        int highestParameterIndex = 0;
        int parameterIndex = 0;
        
        for (int parameterValue : cardParametersValues) {
            if (parameterValue > highestValue) {
                highestValue = parameterValue;
                highestParameterIndex = parameterIndex;
                parameterIndex++;
            }
        }
        return highestParameterIndex;
    }

    public void setTrumperOnTrue() {
        this.trumper = true;
    }

    public boolean getTrumper() {
        return this.trumper;
    }

    public void setStillInGame(boolean trueOrFalse) {
        this.stillInGame = trueOrFalse;
    }

    public boolean getStillInGame() {
        return this.stillInGame;
    }
}   