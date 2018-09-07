package main.cards;

import java.util.*;

public class Card implements Comparable<Card> {

    private int firstParameter;
    private int secondParameter;
    private int thirdParameter;
    private int fourthParameter;
    private String name;
    private int trump;
    public Map<String,Integer> cardParameters;

    Card(CardType type) {
        Random rand = new Random();
        this.name = type.name;
        this.firstParameter = rand.nextInt(4) + type.parameteresFactor[0];
        this.secondParameter = rand.nextInt(4) + type.parameteresFactor[1];
        this.thirdParameter = rand.nextInt(4) + type.parameteresFactor[2];
        this.fourthParameter = rand.nextInt(4) + type.parameteresFactor[3];
        this.cardParameters = new HashMap<>();
        this.cardParameters.put("First Parameter", firstParameter);
        this.cardParameters.put("Second Parameter", secondParameter);
        this.cardParameters.put("Third Parameter", thirdParameter);
        this.cardParameters.put("Forth Parameter", fourthParameter);
    }

    public int getFirstParameter() {
        return this.firstParameter;
    }

    public int getSecondParameter() {
        return this.secondParameter;
    }

    public int getThirdParameter() {
        return this.thirdParameter;
    }

    public int getFourthParameter() {
        return this.fourthParameter;
    }

    public int getParameter(int index) {
        switch (index) {
            case 1:
                return this.getFirstParameter();
                
            case 2:
                return this.getSecondParameter();
                
            case 3:
                return this.getThirdParameter();
                
            case 4:
                return this.getFourthParameter();
        }
        return -1;
    }

    public String getName() {
        return this.name;
    }

    public Map<String,Integer> getCardParametersAndValues() {
        return cardParameters;
    }
    
    public String[] getCardParameters() {
        String[] cardParameters = {"Old Woman", "Janusz and Grazyna", "Crazy People", "Hooligans"};
        return cardParameters;
    }

    public int[] getCardParametersValues() {
        int[] parameteresValues = {getFirstParameter(), getSecondParameter(), getThirdParameter(), getFourthParameter()};
        return parameteresValues;
    }

    public int compareTo(Card otherCard) {
        return this.getTrump() - otherCard.getTrump();
    }

    public int getTrump() {
        return this.trump;
    }

    public void setTrump(int trumpParameter) {
        this.trump = trumpParameter;
    }
}
