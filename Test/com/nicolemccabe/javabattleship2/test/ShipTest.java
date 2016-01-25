package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Ship;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShipTest {
    @Test
    public void testCanSink() {
        Ship ship = new Ship(1);
        ship.logStrike();
        assertTrue(ship.isSunk());
    }

    @Test
    public void notSunkAfterFewerHitsThanSize() {
        Ship ship = new Ship(2);
        ship.logStrike();
        assertFalse(ship.isSunk());
    }

    @Test
    public void stillSunkIfStruckExtraTimes() {
        Ship ship = new Ship(2);
        ship.logStrike();
        ship.logStrike();
        ship.logStrike();
        assertTrue(ship.isSunk());
    }

}
