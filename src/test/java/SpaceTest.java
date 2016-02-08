package test.java;

import main.java.Ship;
import main.java.Space;
import main.java.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpaceTest {
    Space space;
    Ship ship = new Ship("test", 1);

    @Before
    public void setup() {
        space = new Space();
    }

    @Test
    public void testSpaceState() {
        assertEquals(State.WATER, space.getState());
    }

    @Test
    public void testUpdatesStateToMiss(){
        space.logStrike();
        assertEquals(State.MISS, space.getState());
    }

    @Test
    public void testUpdatesStateToShip() {
        space.setShip(ship);
        assertEquals(State.SHIP, space.getState());
    }

    @Test
    public void testUpdatesStateToHit() {
        space.setShip(new Ship("test", 2));
        space.logStrike();
        assertEquals(State.HIT, space.getState());
    }

    @Test
    public void testUpdatesStateToSunk() {
        space.setShip(ship);
        space.logStrike();
        assertTrue(space.shipIsSunk());
    }

    @Test
    public void testNonexistentShipsAreNotSunk() {
        assertFalse(space.shipIsSunk());
    }

    @Test
    public void shootingWhileInStateMissBreaksNothing() {
        space.logStrike();
        space.logStrike();
    }

    @Test
    public void testMultipleSpacesGetSunk() {
        Ship ship = new Ship("test", 2);
        space.setShip(ship);

        Space space2 = new Space();
        space2.setShip(ship);

        space.logStrike();
        space2.logStrike();

        assertEquals(State.SUNK, space.getState());

    }

    @Test
    public void testHitsNotContagious() {
        Ship ship = new Ship("test", 2);
        space.setShip(ship);

        Space space2 = new Space();
        space2.setShip(ship);

        space.logStrike();

        assertEquals(State.SHIP, space2.getState());
    }
}
