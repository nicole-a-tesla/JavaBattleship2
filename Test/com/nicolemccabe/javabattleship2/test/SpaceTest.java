package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Ship;
import com.nicolemccabe.javabattleship2.Space;
import com.nicolemccabe.javabattleship2.State;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpaceTest {
    Space space;
    Ship ship = new Ship(1);

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
        space.setShip(new Ship(2));
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
}
