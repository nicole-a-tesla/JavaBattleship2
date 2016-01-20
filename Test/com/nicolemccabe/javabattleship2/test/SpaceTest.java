package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Space;
import com.nicolemccabe.javabattleship2.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SpaceTest {
    Space s;

    @Before
    public void setup() {
        s = new Space();
    }

    @Test
    public void testSpaceState() {
        assertEquals(State.WATER, s.getState());
    }

    @Test
    public void testUpdatesStateIfStruck(){
        s.logStrike();
        assertEquals(State.MISS, s.getState());
    }

    @Test
    public void testSpaceInitsWithoutShip() {
        assertFalse(s.hasShip());
    }

    @Test
    public void testSpaceCanSetShip() {
        s.setShip();
        assertTrue(s.hasShip());
    }
}
