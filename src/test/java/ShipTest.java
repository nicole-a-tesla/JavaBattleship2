package test.java;

import main.java.Ship;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShipTest {
    @Test
    public void testCanSink() {
        Ship ship = new Ship("test", 1);
        ship.logStrike();
        assertTrue(ship.isSunk());
    }

    @Test
    public void notSunkAfterFewerHitsThanSize() {
        Ship ship = new Ship("test", 2);
        ship.logStrike();
        assertFalse(ship.isSunk());
    }

    @Test
    public void stillSunkIfStruckExtraTimes() {
        Ship ship = new Ship("test", 2);
        ship.logStrike();
        ship.logStrike();
        ship.logStrike();
        assertTrue(ship.isSunk());
    }

    @Test
    public void accessibleSize() {
        Ship ship = new Ship("test", 3);
        assertEquals(3, ship.getSize());
    }

}
