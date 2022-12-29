package battleship;


import com.sun.corba.se.impl.encoding.CodeSetConversion;

import java.util.Random;

public class Ocean {
    // Used to quickly determine which ship is in any given location
    static final int OCEAN_SIZE = 10;
    private Ship[][] ships = new Ship[OCEAN_SIZE][OCEAN_SIZE];

    // The total number of shots fired by the user
    private int shotsFired;

    // The number of times a shot hit a ship. If the user shoots the same part of a ship more than once, every hit
    // is counted, even though additional “hits” don’t do the user any good.
    private int hitCount;

    // The number of ships sunk (10 ships in all)
    private int shipsSunk;

    // boolean map for hitting history:
    boolean[][] isShot = new boolean[OCEAN_SIZE][OCEAN_SIZE];


    /**
     * Creates an ”empty” ocean
     */
    public Ocean() {
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
        helper(ships);
    }


    /**
     * Fill the ships array with EmptySea objects
     * @param ships: The map of the ocean
     */
    private void helper(Ship[][] ships) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Ship empty  = new EmptySea();
                empty.setBowRow(i);
                empty.setBowColumn(j);
                empty.setHorizontal(true);
                ships[i][j] = empty;
            }
        }
    }


    /**
     * Place all ten ships randomly on the (initially empty) ocean. Place larger ships before smaller ones,
     * or you may end up with no legal place to put a large ship. You will want to use the Random class in the java.
     * util package, so look that up in the Java API.
     */
    public void placeAllShipsRandomly() {
        Random random = new Random();
        int newRow = 0;
        int newCol = 0;
        boolean newHorizontal = false;
        boolean flag = false;

        // then we place ships
        int numOfShip = 0;
        // place battleships
        while (numOfShip < 1) {
            Battleship battleship = new Battleship();
            newRow = random.nextInt(10);
            newCol = random.nextInt(10);
            newHorizontal = random.nextInt(2) == 1;
            if (battleship.okToPlaceShipAt(newRow, newCol, newHorizontal, this)) {
                battleship.placeShipAt(newRow, newCol, newHorizontal, this);
                numOfShip++;
            }
        }

        // place cruisers:
        while (numOfShip < 3) {
            Cruiser cruiser = new Cruiser();
            newRow = random.nextInt(10);
            newCol = random.nextInt(10);
            newHorizontal = random.nextInt(2) == 1;
            if (cruiser.okToPlaceShipAt(newRow, newCol, newHorizontal, this)) {
                cruiser.placeShipAt(newRow, newCol, newHorizontal, this);
                numOfShip++;
            }
        }

        // place destroyers:
        while (numOfShip < 6) {
            Destroyer des = new Destroyer();
            newRow = random.nextInt(10);
            newCol = random.nextInt(10);
            newHorizontal = random.nextInt(2) == 1;
            if (des.okToPlaceShipAt(newRow, newCol, newHorizontal, this)) {
                des.placeShipAt(newRow, newCol, newHorizontal, this);
                numOfShip++;
            }
        }

        // place submarines:
        while (numOfShip < 10) {
            Submarine sub = new Submarine();
            newRow = random.nextInt(10);
            newCol = random.nextInt(10);
            newHorizontal = random.nextInt(2) == 1;
            if (sub.okToPlaceShipAt(newRow, newCol, newHorizontal, this)) {
                sub.placeShipAt(newRow, newCol, newHorizontal, this);
                numOfShip++;
            }
        }

    }


    /**
     * Returns true if the given location contains a ship, false if it does not
     * @param row: the row index of the input position
     * @param column: the column index of the input position
     */
    public boolean isOccupied(int row, int column) {
        if ((row < 0 || row > 9) || (column < 0 || column > 9)) {
            return true;
        }
        return !ships[row][column].getShipType().equals("empty");
    }


    /**
     * Returns true if the given location contains a ”real” ship, still a float, (not an EmptySea), false if it does not.
     * In addition, this method updates the number of shots that have been fired, and the number of hits.
     * @param row: the row index of the input position
     * @param column: the column index of the input position
     */
    boolean shootAt(int row, int column) {
        isShot[row][column] = true;
        shotsFired++;
        Ship ship = ships[row][column];
        if (!isOccupied(row, column)) {
            return false;
        }
        hitCount++;
        boolean flag = ship.shootAt(row, column);
        if (ship.isSunk()) {
            shipsSunk++;
        }
        return flag;
    }


    /**
     * Returns the number of shots fired (in the game)
     * @return
     */
    public int getShotsFired() {
        return this.shotsFired;
    }


    /**
     * Returns the number of hits recorded (in the game). All hits are counted,
     * not just the first time a given square is hit.
     * @return
     */
    public int getHitCount() {
        return this.hitCount;
    }


    /**
     * Returns the number of ships sunk (in the game)
     * @return
     */
    public int getShipsSunk(){
        return this.shipsSunk;
    }


    /**
     * Returns true if all ships have been sunk, otherwise false
     * @return
     */
    public boolean isGameOver() {
        return shipsSunk == 10;
    }


    /**
     * Returns the 10x10 array of Ships. The methods in the Ship class that take an Ocean parameter need to be able to
     * look at the contents of this array; the placeShipAt() method even needs to modify it.
     * While it is undesirable to allow methods in one class to directly access instance variables in another class,
     * sometimes there is just no good alternative.
     * @return
     */
    public Ship[][] getShipArray(){
        return ships;
    }


    /**
     * Prints the Ocean. To aid the user, row numbers should be displayed along the left edge of the array,
     * and column numbers should be displayed along the top. Numbers should be 0 to 9, not 1 to 10.
     */
    public void print() {
        String row0 = "   0  1  2  3  4  5  6  7  8  9";
        System.out.println(row0);
        for(int i = 0; i < 10; i++) {
            String row_i = i + "";
            for (int j = 0; j < 10; j++) {
                // if there is a real ship in this position: if it is not discovered, then remain * else "x" or "s"
                if (!ships[i][j].getShipType().equals("empty")) {
                    // find the ship
                    Ship ship = ships[i][j];
                    // if the ship is sunk: append "s" and continue
                    if (ship.isSunk()) {
                        row_i += "  " + ship.toString();
                        continue;
                    }
                    // find whether this part is hit according to the hit array
                    int bowRow = ship.getBowRow();
                    int bowColumn = ship.getBowColumn();
                    boolean isHit = ship.isHorizontal() ? ship.getHit()[bowColumn - j] : ship.getHit()[bowRow - i];
                    if (isHit) {
                        row_i += "  " + ships[i][j].toString();
                    } else {
                        row_i += "  ·";
                    }
                } else {
                    // if this position is an empty ship
                    if (isShot[i][j]) {
                        row_i += "  -";
                        continue;
                    }
                    String current = ships[i][j].toString();
                    row_i += "  " + current;
                }
            }
            System.out.println(row_i);
        }
    }


    /**
     * USED FOR DEBUGGING PURPOSES ONLY.
     * Like the print() method, this method prints the Ocean with row numbers displayed along the left edge of the array,
     * and column numbers displayed along the top. Numbers should be 0 to 9, not 1 to 10.
     * The top left corner square should be 0, 0.
     */
    public void printWithShips() {
        // define the map row-by-row
        String row0 = " 0  1  2  3  4  5  6  7  8  9";
        System.out.println(row0);

        for(int i = 0; i < 10; i++) {
            String row_i = i + "";
            for (int j = 0; j < 10; j++) {
                if (ships[i][j].getShipType().equals("empty")) {
                    // case 1: empty ship
                    row_i += "  ";
                } else if (ships[i][j].getShipType().equals("battleship")) {
                    // case2: battleship
                    row_i += " b";
                } else if (ships[i][j].getShipType().equals("cruiser")) {
                    // case 3: cruiser
                    row_i += " c";
                } else if (ships[i][j].getShipType().equals("destroyer")) {
                    // case 4: destroyer
                    row_i += " d";
                } else if (ships[i][j].getShipType().equals("submarine")) {
                    // case 5: submarine
                    row_i += " s";
                }
            }
            System.out.println(row_i);
        }

    }




}
