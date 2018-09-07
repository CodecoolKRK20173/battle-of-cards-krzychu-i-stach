package main.cards;

public enum CardType {
        
    TYPE1(new int[] {6,1,1,1}, "PKS Torun"),
    TYPE2(new int[] {1,6,1,1}, "PKS Leba"),
    TYPE3(new int[] {1,1,6,1}, "PKS Tworki"),
    TYPE4(new int[] {1,1,1,6}, "PKS Widzew Stadium");
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
