package main.cards;

import java.util.Random;

public class Card implements Comparable {

    private int firstParameter;
    private int secondParameter;
    private int thirdParameter;
    private int fourthParameter;
    private String name;
    // private int[] arrayOfParameter;
    private int trump;

    Card(CardType type) {
        Random rand = new Random();
        this.name = type.name;
        // arrayOfParameter = new int[4];
       this.firstParameter = rand.nextInt(4) + type.parameteresFactor[0];
    //    this.arrayOfParameter[0] = firstParameter;
       this.secondParameter = rand.nextInt(4) + type.parameteresFactor[1];
    //    this.arrayOfParameter[1] = secondParameter;
       this.thirdParameter = rand.nextInt(4) + type.parameteresFactor[2];
    //    this.arrayOfParameter[2] = thirdParameter;
       this.fourthParameter = rand.nextInt(4) + type.parameteresFactor[3];
    //    this.arrayOfParameter[3] = fourthParameter;
        
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

    public int compareTo(Object otherCard) {
        Card cardToCompare = (Card) otherCard;
        if (this.trump == cardToCompare.getTrump())
            return 0;
        else if (this.trump < cardToCompare.getTrump())
            return -1;
        else 
             return 1;
    }

    public int getTrump() {
        return this.trump;
    }

    public void setTrump(int trumpParameter) {
        this.trump = trumpParameter;
    }

}