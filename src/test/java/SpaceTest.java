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
    public void testNonexistantShipsAreNotSunk() {
        assertFalse(space.shipIsSunk());
    }

    @Test
    public void shootingWhileInStateMissBreaksNothing() {
        space.logStrike();
        space.logStrike();
    }
}
