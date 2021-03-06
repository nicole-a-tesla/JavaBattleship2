package test.java;

import main.java.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BoardTest {
    Board b = new Board(new Fleet(), 10);

    @Test
    public void hasSize() {
        assertEquals(10, b.getSize());
        b = new Board(new Fleet(), 7);
        assertEquals(7, b.getSize());
    }

    @Test
    public void creates10x10grid() {
        int rowSize = b.getGrid().size();
        assertEquals(10 , rowSize);
        ArrayList col = (ArrayList) b.getGrid().get(0);
        assertEquals(10 , col.size());
    }

    @Test
    public void gridIsFullOfSpaces() {
        ArrayList<ArrayList> grid = b.getGrid();
        ArrayList row = grid.get(0);

        Class contentsClass = row.get(0).getClass();

        assertEquals(Space.class, contentsClass);
    }

    @Test
    public void canGetCoordState() {
        State state = b.getStateAt(0,0);
        assertEquals(State.WATER, state);
    }

    @Test
    public void canTellSpaceItWasStruckAndMissed() {
        State state = b.logStrikeAt(0,0);
        assertEquals(State.MISS, state);
    }

    @Test
    public void reportsShipWasSunk() {
        b.setShipAt(new Ship("test", 1), 0,0);
        b.logStrikeAt(0,0);
        assertEquals(State.SUNK, b.getStateAt(0,0));
    }

    @Test
    public void canSetShipAtCoords() {
        State state = b.setShipAt(new Ship("test", 1), 0,0);
        assertEquals(State.SHIP, state);
    }

    @Test
    public void hasFiveShips() {
        assertEquals(5, b.getShips().size());
    }

    @Test
    public void reportsAllShipsAreNotSunk() {
        assertFalse(b.allSunk());
    }


    @Test
    public void reportsAllShipsAreSunk() {
        List<Ship> ships = b.getShips();
        Helpers.sinkThisManyShips(ships, 5);

        assertTrue(b.allSunk());
    }

    @Test
    public void reportsNotAllSunkIfOnlySomeSunk() {
        List<Ship> ships = b.getShips();
        Helpers.sinkThisManyShips(ships, 4);

        assertFalse(b.allSunk());
    }

    @Test
    public void testBoardSetup() {
        b.setAllShipsAtRandom();
        int numOfSpacesWithShips = 0;

        for (int x=0; x<10; x++) {
            for (int y=0; y<10; y++) {
                if (b.getStateAt(x,y) == State.SHIP) {
                    numOfSpacesWithShips++;
                }
            }
        }
        Assert.assertEquals(17, numOfSpacesWithShips);
    }

    @Test
    public void testHorizontalSet() {
        Board board = new Board(new Fleet(), 2);
        Ship ship = new Ship("ship", 2);
        ShipSetArgs args = new ShipSetArgs(ship, 0, 0, "horizontal");
        board.attemptSet(args, 0);

        assertEquals(board.getStateAt(0,0), State.SHIP);
        assertEquals(board.getStateAt(1,0), State.SHIP);

        assertEquals(board.getStateAt(0,1), State.WATER);
        assertEquals(board.getStateAt(1,1), State.WATER);
    }

    @Test
    public void testVerticalSet() {
        Board board = new Board(new Fleet(), 2);
        Ship ship = new Ship("ship", 2);
        ShipSetArgs args = new ShipSetArgs(ship, 0, 0, "vertical");
        board.attemptSet(args, 0);

        assertEquals(board.getStateAt(0,0), State.SHIP);
        assertEquals(board.getStateAt(0,1), State.SHIP);

        assertEquals(board.getStateAt(1,0), State.WATER);
        assertEquals(board.getStateAt(1,1), State.WATER);
    }
}

