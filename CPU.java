public class CPU extends Player {

    CPU() {
        this.hand = new Hand();
    }


    CPU(String name) {
        this.name = name;
        this.hand = new Hand();
    }
}