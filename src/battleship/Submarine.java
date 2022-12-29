package battleship;

public class Submarine extends Ship{
    // A ship of length 1
    public Submarine() {
        // the length of a submarine is 1
        super(1);
    }


    @Override
    public String getShipType() {
        return "submarine";
    }
}
