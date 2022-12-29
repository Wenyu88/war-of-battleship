package battleship;

import java.util.HashMap;
import java.util.Map;

public abstract class Ship {
    /*
    Fields should be declared private unless there is a good reason for not doing so. This is known as “encapsulation”,
    which is the process of making the fields in a class private and providing access to the fields via public methods
    (e.g. getters and setters).
     */
    // The row that contains the bow (front part of the ship)
    private int bowRow;

    // The column that contains the bow (front part of the ship)
    private int bowColumn;

    // The length of the ship
    private int length;

    // A boolean that represents whether the ship is going to be placed horizontally or vertically
    private boolean horizontal;

    // An array of booleans that indicate whether that part of the ship has been hit or not
    private boolean[] hit;

    // This constructor sets the length property of the particular ship and initializes the hit array based on that length
    public Ship(int length) {
        this.length = length;
        this.hit = new boolean[length];
    }

    /**
     * getLength(): Returns the ship length
     */
    public int getLength() {
        return length;
    }

    /**
     * getBowRow(): Returns the row corresponding to the position of the bow
     */
    public int getBowRow() {
        return bowRow;
    }

    /**
     * getBowColumn(): Returns the bow column location
     */
    public int getBowColumn() {
        return bowColumn;
    }

    /**
     * getHit(): Returns the hit array
     */
    public boolean[] getHit() {
        return hit;
    }

    /**
     * isHorizontal(): Returns whether the ship is horizontal or not
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * setBowRow(): Sets the value of bowRow
     * @param row: row that contains the bow
     */
    public void setBowRow(int row) {
        this.bowRow = row;
    }

    /**
     * setBowColumn(): Sets the value of bowColumn
     * @param column: column that contains the bow
     */
    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    /**
     * setHorizontal(): Sets the value of the instance variable horizontal
     * @param horizontal: A boolean that represents whether the ship is going to be placed horizontally or vertically
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * getShipType(): Returns the type of ship as a String
     */
    public abstract String getShipType();

    /**
     * okToPlaceShipAt(): Based on the given row, column, and orientation, returns true if it is okay to put a ship of
     *                    this length with its bow in this location; false otherwise.
     * @param row: row of the bow
     * @param column: column of the bow
     * @param horizontal: the orientation of the ship
     * @param ocean: map
     * @return ans: true if it's ok to place the ship, otherwise false
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // corner case: if row or column is out of boundary:
        if ((row > 9 || row < 0) || (column > 9 || column < 0)) {
            return false;
        }
        // case 1: if the position of the bow has been occupied:
        if (ocean.isOccupied(row, column)) {
            return false;
        } else {
            // case 2: traverse along the orientation, if it will exceed the boundary, return false.
            // bow is on the right end
            int currentLen = this.length;

            if (horizontal) {
                if (column - currentLen + 1 < 0) {
                    return false;
                }
            } else {
                if (row - currentLen + 1 < 0) {
                    return false;
                }
            }

            // case 3: traverse along the orientation, if another ship already occupied this position or adjacent to it, return false.
            for (int i = 0; i < currentLen; i++) {
                if (horizontal) {
                    // case 3.1: if a position is occupied:
                    int currentRow = row;
                    int currentColumn = column - i;
                    if (ocean.isOccupied(currentRow, currentColumn)) {
                        return false;
                    }
                    // case 3.2: search the nearby area of the ship:
                    // trick: we only need to check the end points since the length will be no more than 4:
                    if (i == 0) {
                        // case 3.2.1 search the nearby area of the bow point:
                        if (!checkBow(currentRow, currentColumn, true, ocean)) {
                            return false;
                        }
                    }
                    if (i == currentLen - 1){
                        // case 3.2.2 search the nearby area of the end point
                        if (!checkEnd(currentRow, currentColumn, true, ocean)) {
                            return false;
                        }
                    }
                } else {
                    // similarly, in horizontal direction:
                    int currentRow = row - i;
                    int currentColumn = column;
                    if (ocean.isOccupied(currentRow, currentColumn)) {
                        return false;
                    }
                    if (i == 0) {
                        if (!checkBow(currentRow, currentColumn, false, ocean)) {
                            return false;
                        }
                    }
                    if (i == currentLen - 1) {
                        if (!checkEnd(currentRow, currentColumn, false, ocean)) {
                            return false;
                        }
                    }
                }
            }
            // case 4: return true
            return true;
        }
    }

    private boolean checkBow(int row, int col, boolean isHorizontal, Ocean ocean) {
        if (isHorizontal) {
            int[] point1 = {row - 1, col - 1};
            int[] point2 = {row - 1, col};
            int[] point3 = {row - 1, col + 1};
            int[] point4 = {row, col + 1};
            int[] point5 = {row + 1, col + 1};
            int[] point6 = {row + 1, col};
            int[] point7 = {row + 1, col - 1};
            return checkPoint(point1, ocean) && checkPoint(point2, ocean) && checkPoint(point3, ocean)
                    && checkPoint(point4, ocean) && checkPoint(point5, ocean)
                    && checkPoint(point6, ocean) && checkPoint(point7, ocean);
        } else {
            int[] point1 = {row - 1, col + 1};
            int[] point2 = {row, col + 1};
            int[] point3 = {row + 1, col + 1};
            int[] point4 = {row + 1, col};
            int[] point5 = {row + 1, col - 1};
            int[] point6 = {row, col - 1};
            int[] point7 = {row - 1, col - 1};
            return checkPoint(point1, ocean) && checkPoint(point2, ocean) && checkPoint(point3, ocean)
                    && checkPoint(point4, ocean) && checkPoint(point5, ocean)
                    && checkPoint(point6, ocean) && checkPoint(point7, ocean);
        }
    }

    private boolean checkEnd(int row, int col, boolean isHorizontal, Ocean ocean) {
        if (isHorizontal) {
            int[] point1 = {row + 1, col + 1};
            int[] point2 = {row + 1, col};
            int[] point3 = {row + 1, col - 1};
            int[] point4 = {row, col - 1};
            int[] point5 = {row - 1, col - 1};
            int[] point6 = {row - 1, col};
            int[] point7 = {row - 1, col + 1};
            return checkPoint(point1, ocean) && checkPoint(point2, ocean) && checkPoint(point3, ocean)
                    && checkPoint(point4, ocean) && checkPoint(point5, ocean)
                    && checkPoint(point6, ocean) && checkPoint(point7, ocean);
        } else {
            int[] point1 = {row + 1, col - 1};
            int[] point2 = {row,  col - 1};
            int[] point3 = {row - 1, col - 1};
            int[] point4 = {row - 1, col};
            int[] point5 = {row - 1, col + 1};
            int[] point6 = {row , col + 1};
            int[] point7 = {row + 1, col + 1};
            return checkPoint(point1, ocean) && checkPoint(point2, ocean) && checkPoint(point3, ocean)
                    && checkPoint(point4, ocean) && checkPoint(point5, ocean)
                    && checkPoint(point6, ocean) && checkPoint(point7, ocean);
        }
    }

    private boolean checkPoint(int[] point, Ocean ocean) {

        int row = point[0];
        int col = point[1];
        if ((row < 0 || row > 9) || (col < 0 || col > 9)) {
            return true;
        }

        if (ocean.isOccupied(point[0], point[1])) {
            return false;
        }
        return true;
    }



    /**
     * placeShipAt(): “Puts” the ship in the ocean. This involves giving values to the bowRow, bowColumn,
     * and horizontal instance variables in the ship,
     * and it also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array
     * in the Ocean object. (Note: This will be as many as four identical references;
     * you can’t refer to a ”part” of a ship, only to the whole ship.)
     * @param row: A boolean that represents whether the ship is going to be placed horizontally or vertically
     * @param column:
     * @param horizontal:
     * @param ocean
     * @return
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // check feasibility:
        if (!okToPlaceShipAt(row, column, horizontal, ocean)) {
            return;
        }
        // after checking whether a position is feasible or not, put this ship:
        int currentLen = this.length;
        setBowRow(row);
        setBowColumn(column);
        setHorizontal(horizontal);
        // case 1: if the orientation is horizontal:
        if (horizontal) {
            for (int i = 0; i < currentLen; i++) {
                ocean.getShipArray()[row][column - i] = this;

            }
        } else {
            // case 2: if the orientation is vertical:
            for (int i = 0; i < currentLen; i++) {
                ocean.getShipArray()[row - i][column] = this;

            }
        }

        System.out.println(this.getShipType() + " is put at " + bowRow + " " + bowColumn + " " +
                (isHorizontal() ? "horizontally" : "vertically"));
    }


    /**
     * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk,
     * mark that part of the ship as “hit” (in the hit array, index 0 indicates the bow) and return true;
     * otherwise return false.
     * @param row:
     * @param column:
     * @return:
    */
    public boolean shootAt(int row, int column) {
        // check whether this area has a ship:
        boolean isOccupied = false;
        if (this.horizontal) {
            for (int i = 0; i < length; i++) {
                if (bowRow == row && bowColumn - i == column) {
                    isOccupied = true;
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (bowRow - i == row && bowColumn == column) {
                    isOccupied = true;
                }
            }
        }

        // if there is not a ship:
        if (!isOccupied) {
            System.out.println("Nothing is hit at " + row + " " + column);
            return false;
        } else {
            // if there is a ship
            if (!isSunk()) {
                if (this.horizontal) {
                    hit[bowColumn - column] = true;
                    System.out.println(this.getShipType() + " is hit!");
                    return true;
                } else {
                    hit[bowRow - row] = true;
                    System.out.println(this.getShipType() + " is hit!");
                    return true;
                }
            }
        }
        // if this ship is sunk
        return false;
    }


    /**
     * Return true if every part of the ship has been hit, false otherwise
     * @return :
     */
    public boolean isSunk() {
        for (boolean part : this.hit) {
            if (!part) {
                // if one part is good, return false\
                return false;
            }
        }
        // if every part was hit, return true.
        return true;
    }


    /**
     * Return "s" if the ship has been sunk and "x" if it has not been sunk
     */
    @Override
    public String toString(){
        return isSunk() ? "s" : "x";
    }

}


