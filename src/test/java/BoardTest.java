package test.java;

import main.java.Board;
import main.java.Ship;
import main.java.Space;
import main.java.State;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BoardTest {
    Board b = new Board(10);

    @Test
    public void hasSize() {
        assertEquals(10, b.size);
        b = new Board(7);
        assertEquals(7, b.size);
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
        b.setShipAt(new Ship(1), 0,0);
        b.logStrikeAt(0,0);
        assertEquals(State.SUNK, b.getStateAt(0,0));
    }

    @Test
    public void canSetShipAtCoords() {
        State state = b.setShipAt(new Ship(1), 0,0);
        assertEquals(State.SHIP, state);
    }

    @Test
    public void hasFiveShips() {
        assertEquals(5, b.getShips().length);
    }

    @Test
    public void reportsAllShipsAreNotSunk() {
        assertFalse(b.allSunk());
    }

    @Test
    public void reportsAllShipsAreSunk() {
        Ship[] ships = b.getShips();

        for (int i=0; i<ships.length; i++) {
            b.setShipAt(ships[i], i, i);
            b.logStrikeAt(i, i);
        }

        assertTrue(b.allSunk());
    }

    @Test
    public void reportsNotAllSunkIfOnlySomeSunk() {
        Ship[] ships = b.getShips();

        for (int i=0; i<ships.length; i++) {
            b.setShipAt(ships[i], i, i);
        }

        b.logStrikeAt(0,0);
        b.logStrikeAt(4,4);

        assertFalse(b.allSunk());
    }
}