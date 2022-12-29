package battleship;

public class EmptySea extends Ship{
    /*
    Describes a part of the ocean that doesn’t have a ship in it. (It seems silly to have the lack of a ship be a type
    of ship, but this is a trick that simplifies a lot of things. This way, every location in the ocean contains a
    “ship” of some kind.)
     */

    public EmptySea() {
        super(1);
    }

    @Override
    public String getShipType() {
        return "empty";
    }


    // return nothing to shoot
    @Override
    public boolean shootAt(int row, int column) {
        this.getHit()[0] = true;
        return false;
    }


    // return nothing is sunk
    @Override
    public boolean isSunk() {
        return false;
    }


    // return "-"
    @Override
    public String toString() {
        if (this.getHit()[0]) {
            return "-";
        }
        return "·";
    }

}
