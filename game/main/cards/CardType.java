package main.cards;

public enum CardType {
        
    TYPE1(new int[] {6,1,1,1}, "Type1"),
    TYPE2(new int[] {1,6,1,1}, "Type2"),
    TYPE3(new int[] {1,1,6,1}, "Type3"),
    TYPE4(new int[] {1,1,1,6}, "Type4");
    // parameters of cards configuration

    int[] parameteresFactor;
    String name;

    private CardType(int[] arr, String name) {
        this.parameteresFactor = arr;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
