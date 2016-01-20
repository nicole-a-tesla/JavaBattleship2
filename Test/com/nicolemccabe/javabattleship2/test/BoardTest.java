package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Board;
import com.nicolemccabe.javabattleship2.Space;
import com.nicolemccabe.javabattleship2.State;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

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
    public void canTellSpaceItWasHit() {
        b.logStrikeAt(0,0);
        State state = b.getStateAt(0,0);
        assertEquals(State.MISS, state);
    }
}
