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

    public String getName() {
        return this.name;
    }


}