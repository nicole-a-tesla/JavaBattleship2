package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Board;
import com.nicolemccabe.javabattleship2.Space;
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

}
