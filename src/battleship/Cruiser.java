package battleship;

public class Cruiser extends Ship{
    // A ship of length 3
    public Cruiser() {
        super(3);
    }


    @Override
    public String getShipType() {
        return "cruiser";
    }
}
