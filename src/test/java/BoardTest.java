package test.java;

import main.java.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BoardTest {
    Board b = new Board(new Fleet(), 10);

    @Test
    public void hasSize() {
        assertEquals(10, b.size);
        b = new Board(new Fleet(), 7);
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

        for (int i=0; i<ships.size(); i++) {
            b.setShipAt(ships.get(i), i, i);
            b.logStrikeAt(i, i);
        }

        assertTrue(b.allSunk());
    }

    @Test
    public void reportsNotAllSunkIfOnlySomeSunk() {
        List<Ship> ships = b.getShips();

        for (int i=0; i<ships.size(); i++) {
            b.setShipAt(ships.get(i), i, i);
        }

        b.logStrikeAt(0,0);
        b.logStrikeAt(4,4);

        assertFalse(b.allSunk());
    }

    @Test
    public void testBoardSetup() {
        b.setAllShipsAtRandom();
        int numOfShipsSet = 0;

        for (int x=0; x<10; x++) {
            for (int y=0; y<10; y++) {
                if (b.getStateAt(x,y) == State.SHIP) {
                    numOfShipsSet++;
                }
            }
        }
        Assert.assertEquals(5, numOfShipsSet);
    }

    @Test
    public void testFleet() {
    }

}

