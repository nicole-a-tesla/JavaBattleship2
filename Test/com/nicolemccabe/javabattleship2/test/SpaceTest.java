package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Space;
import com.nicolemccabe.javabattleship2.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void testUpdatesStateToMiss(){
        s.logStrike();
        assertEquals(State.MISS, s.getState());
    }

    @Test
    public void testUpdatesStateToShip() {
        s.setShip();
        assertEquals(State.SHIP, s.getState());
    }

    @Test
    public void testUpdatesStateToHit() {
        s.setShip();
        s.logStrike();
        assertEquals(State.HIT, s.getState());
    }
}
