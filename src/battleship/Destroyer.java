package battleship;

public class Destroyer extends Ship{
    // A ship of length 2
    public Destroyer() {
        // the length of a destroyer is 2
        super(2);
    }


    // get name of the ship:
    @Override
    public String getShipType() {
        return "destroyer";
    }

}
