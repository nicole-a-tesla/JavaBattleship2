package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Board;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BoardTest {
    @Test
    public void calculatesSize() {
        Board b = new Board(1);
        assertEquals(1, b.size);
        b = new Board(7);
        assertEquals(7, b.size);
    }
}
