package battleship;

import java.util.Scanner;

public class BattleShipGame {
    /**
     * This is the launcher of the game
     */
    public static void main(String[] args) {
        // Initialize the map:
        Ocean ocean = new Ocean();
        // Initialize the ships
        ocean.placeAllShipsRandomly();
        // DEBUG ONLY!!!
        //ocean.printWithShips();
        Scanner sc = new Scanner(System.in);
        // String Position = sc.next();

        while (true) {
            // check if game is over:
            if (ocean.isGameOver()) {
                System.out.println("Congratulation! You win!");
                break;
            }

            // print current ocean map:
            ocean.print();
            // print current shot fired and hit count:
            System.out.println("Hit counts: " + ocean.getHitCount());
            System.out.println("Shots fired: " + ocean.getShotsFired());
            System.out.println("Ship sunk: " + ocean.getShipsSunk());

            while (true) {
                // get the row
                // player need to shoot somewhere
                int row;
                int col;
                while (true) {
                    System.out.println("Which row do you want to shoot (0-9)?");
                    String inputRow = sc.next();
                    row = inputRow.toCharArray()[0] - '0';
                    if (row >= 0 && row <= 9) {
                        break;
                    }
                }

                // get the column
                while (true) {
                    System.out.println("Which column do you want to shoot (0-9)?");
                    String inputCol = sc.next();
                    col = inputCol.toCharArray()[0] - '0';
                    System.out.println(col);
                    if (col >= 0 && col <= 9) {
                        break;
                    }
                }

                if (ocean.isShot[row][col]) {
                    // if a position is chosen before, the user must input a new one
                    System.out.println("You have tried this position! Try another one!");
                } else {
                    // shootAt (row, col)
                    ocean.shootAt(row, col);
                    break;
                }
            }
        }
    }
}
