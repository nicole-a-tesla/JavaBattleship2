package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Space;
import com.nicolemccabe.javabattleship2.State;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SpaceTest {
    @Test
    public void testSpaceState() {
        Space s = new Space();
        assertEquals(State.WATER, s.getState());
    }
}
