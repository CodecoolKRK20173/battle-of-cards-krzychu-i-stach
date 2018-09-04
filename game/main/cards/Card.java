package main.cards;

import java.util.Random;

public class Card {

    private int firstParameter;
    private int secondParameter;
    private int thirdParameter;
    private int fourthParameter;
    private String name;

    Card(CardType type) {
        Random rand = new Random();
        this.name = type.name;
       this.firstParameter = rand.nextInt(4) + type.parameteresFactor[0];
       this.secondParameter = rand.nextInt(4) + type.parameteresFactor[1];
       this.thirdParameter = rand.nextInt(4) + type.parameteresFactor[2];
       this.fourthParameter = rand.nextInt(4) + type.parameteresFactor[3];
        
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
                return topCard.getFirstParameter();
                break;
            case 2:
                return topCard.getSecondParameter();
                break;
            case 3:
                return topCard.getThirdParameter();
                break;
            case 4:
                return topCard.getFourthParameter();
        }
    }

    public String getName() {
        return this.name;
    }

    public int[] getCardParameters() {
        int[] getCardParameters = {firstParameter, secondParameter, thirdParameter, fourthParameter};
        return getCardParameters;
    }


}