package main.players;

import java.util.Random;
import main.cards.*;

public class CPU extends Player {

    private Hand hand;
    private String name;

    public CPU() {
        this.hand = new Hand();
    }

    public CPU(String name) {
        this.name = name;
        this.hand = new Hand();
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
}   