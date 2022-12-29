package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests

		// test submarine
		Ship submarine = new Submarine();
		assertEquals(1, submarine.getLength());

		// test destroyer
		Ship destroyer = new Destroyer();
		assertEquals(2, destroyer.getLength());

		// test cruiser
		Ship cruiser = new Cruiser();
		assertEquals(3, cruiser.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests

		// test submarine
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());

		// test destroyer
		Ship destroyer = new Destroyer();
		row = 7;
		column = 7;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());

		// test cruiser
		Ship cruiser = new Cruiser();
		row = 5;
		column = 5;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());


	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests

		// test submarine
		Ship submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, submarine.getBowColumn());

		// test destroyer
		Ship destroyer = new Destroyer();
		row = 7;
		column = 7;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, destroyer.getBowColumn());

		// test cruiser
		Ship cruiser = new Cruiser();
		row = 5;
		column = 5;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests

		// test battleship when getting hit
		Ship battleship = new Battleship();
		battleship.placeShipAt(5,5,true, ocean);
		battleship.shootAt(5,5);
		boolean[] hits2 =  {true, false, false, false};
		assertArrayEquals(hits2, battleship.getHit());
		battleship.shootAt(5,2);
		hits2[3] = true;
		assertArrayEquals(hits2, battleship.getHit());

		// test submarine when getting hit:
		Ship submarine = new Submarine();
		submarine.placeShipAt(8, 8, false, ocean);
		boolean[] hits3 = {false};
		submarine.shootAt(8,7);
		assertArrayEquals(hits3, submarine.getHit());
		hits3[0] = true;
		submarine.shootAt(8,8);
		assertArrayEquals(hits3, submarine.getHit());

		// test destroyer
		Ship destroyer = new Destroyer();
		boolean[] hits4 = new boolean[2];
		assertArrayEquals(hits4, destroyer.getHit());

		// test cruiser
		Ship cruiser = new Cruiser();
		boolean[] hits5 = new boolean[3];
		assertArrayEquals(hits5, cruiser.getHit());
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests

		// test submarine:
		Ship submarine = new Submarine();
		assertEquals("submarine", submarine.getShipType());

		// test destroyer
		Ship destroyer = new Destroyer();
		assertEquals("destroyer", destroyer.getShipType());

		// test cruiser
		Ship cruiser = new Cruiser();
		assertEquals("cruiser", cruiser.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests:
		// test destroyer:
		Ship des = new Destroyer();
		horizontal = false;
		des.placeShipAt(9, 9, horizontal, ocean);
		assertFalse(des.isHorizontal());

		// test submarine
		Ship submarine = new Submarine();
		horizontal = true;
		submarine.placeShipAt(3, 0, horizontal, ocean);
		assertTrue(submarine.isHorizontal());

		// test cruiser:
		Ship cru = new Cruiser();
		horizontal = false;
		cru.placeShipAt(8, 4, horizontal, ocean);
		assertFalse(cru.isHorizontal());

	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		// test submarine:
		Ship submarine = new Submarine();
		submarine.setBowRow(6);
		assertEquals(6, submarine.getBowRow());

		// test destroyer:
		Ship des = new Destroyer();
		des.setBowRow(9);
		assertEquals(9, des.getBowRow());

		// test cruiser
		Ship cru = new Cruiser();
		cru.setBowRow(3);
		assertEquals(3, cru.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		// test submarine:
		Ship submarine = new Submarine();
		submarine.setBowColumn(6);
		assertEquals(6, submarine.getBowColumn());

		// test destroyer:
		Ship des = new Destroyer();
		des.setBowColumn(9);
		assertEquals(9, des.getBowColumn());

		// test cruiser
		Ship cru = new Cruiser();
		cru.setBowColumn(3);
		assertEquals(3, cru.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		// test submarine:
		Ship submarine = new Submarine();
		submarine.setHorizontal(true);
		assertTrue(submarine.isHorizontal());

		// test destroyer:
		Ship des = new Destroyer();
		des.setHorizontal(false);
		assertFalse(des.isHorizontal());

		// test cruiser
		Ship cru = new Cruiser();
		cru.setHorizontal(false);
		assertFalse(cru.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean

		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests

		// put a submarine at(2,0), which is ok
		Ship submarine = new Submarine();
		row = 2;
		column = 0;
		horizontal = false;
		boolean ok2 = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok2, "OK to place ship here.");

		// put a destroyer at (9,1), which is ok
		Ship cruiser = new Cruiser();
		row = 9;
		column = 1;
		horizontal = true;
		boolean ok3 = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok3, "OK to place ship here.");

	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests

		// put a submarine at (1, 5) horizontally, which is not ok!
		Ship submarine = new Submarine();
		row = 1;
		column = 5;
		horizontal = false;
		boolean ok3 = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place diagonally adjacent below.");

		// put a destroyer at (4,4) horizontally, which is ok!
		Ship destroyer = new Destroyer();
		row = 4;
		column = 4;
		horizontal = false;
		boolean ok4 = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok4, "OK to place ship here.");
		destroyer.placeShipAt(4,4, horizontal, ocean);

		// put a cruiser at (3,3), which is not ok!
		Ship cruiser = new Cruiser();
		row = 3;
		column = 3;
		horizontal = true;
		boolean ok5 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok5, "Not OK to place ship vertically adjacent right.");

		// put a battleship at (1, 9) vertically, which is not ok!
		Ship battleship3 = new Battleship();
		row = 1;
		column = 9;
		horizontal = false;
		boolean ok6 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok6, "It will be out of the boundary!");

	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		assertEquals(battleship, ocean.getShipArray()[0][3]);
		assertEquals(battleship, ocean.getShipArray()[0][2]);
		assertEquals(battleship, ocean.getShipArray()[0][4]);
		assertEquals("empty", ocean.getShipArray()[0][5].getShipType());
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		assertTrue(battleship.shootAt(0, 9));
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship.getHit());

		Ship cru = new Cruiser();
		cru.placeShipAt(5,5, true, ocean);
		assertTrue(cru.shootAt(5,4));
		boolean[] hit2 = {false, true, false};
		assertArrayEquals(cru.getHit(), hit2);

		Ship des = new Destroyer();
		des.placeShipAt(2, 2 , false, ocean);
		assertFalse(des.shootAt(2,1));
		boolean[] hit3 = {true, false};
		assertTrue(des.shootAt(2,2));
		assertArrayEquals(des.getHit(), hit3);


	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(submarine, ocean.getShipArray()[3][3]);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());

		//TODO
		//More tests
		Ship battleship = new Battleship();
		row = 0;
		column = 3;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 3));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 2));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 1));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 0));
		assertTrue(battleship.isSunk());

		// submarine test:
		Ship sub = new Submarine();
		sub.placeShipAt(0, 5, true, ocean);
		assertFalse(sub.isSunk());
		assertTrue(sub.shootAt(0, 5));
		assertTrue(sub.isSunk());

		// destroyer test
		Ship des = new Destroyer();
		des.placeShipAt(3,1,false, ocean);
		assertFalse(des.isSunk());
		assertTrue(des.shootAt(3,1));
		assertFalse(des.isSunk());
		assertTrue(des.shootAt(2,1));
		assertTrue(des.isSunk());

		// cruiser test:
		Ship cru = new Cruiser();
		cru.placeShipAt(5, 5, false, ocean);
		assertFalse(cru.isSunk());
		assertTrue(cru.shootAt(5,5));
		assertFalse(cru.isSunk());
		assertTrue(cru.shootAt(4,5));
		assertFalse(cru.isSunk());
		assertTrue(cru.shootAt(3,5));
		assertTrue(cru.isSunk());


	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests

		Ship battleship2 = new Battleship();
		row = 0;
		column = 3;
		horizontal = true;
		battleship2.placeShipAt(row, column, horizontal, ocean);
		assertEquals("x", battleship2.toString());
		assertTrue(battleship2.shootAt(0, 3));
		assertEquals("x", battleship2.toString());
		assertTrue(battleship2.shootAt(0, 2));
		assertEquals("x", battleship2.toString());
		assertTrue(battleship2.shootAt(0, 1));
		assertEquals("x", battleship2.toString());
		assertTrue(battleship2.shootAt(0, 0));
		assertEquals("s", battleship2.toString());

		// cruiser test:
		Ship cru = new Cruiser();
		cru.placeShipAt(5, 5, false, ocean);
		assertEquals("x", cru.toString());
		assertTrue(cru.shootAt(5,5));
		assertEquals("x", cru.toString());
		assertTrue(cru.shootAt(4,5));
		assertEquals("x", cru.toString());
		assertTrue(cru.shootAt(3,5));
		assertEquals("s", cru.toString());

		// destroyer test:
		Ship des = new Destroyer();
		des.placeShipAt(3,1,false, ocean);
		assertEquals("x", des.toString());
		assertTrue(des.shootAt(3,1));
		assertEquals("x", des.toString());
		assertTrue(des.shootAt(2,1));
		assertEquals("s", cru.toString());

		// submarine test:
		Ship sub = new Submarine();
		sub.placeShipAt(0, 5, true, ocean);
		assertEquals("x", sub.toString());
		assertTrue(sub.shootAt(0, 5));
		assertEquals("s", des.toString());

	}

}
