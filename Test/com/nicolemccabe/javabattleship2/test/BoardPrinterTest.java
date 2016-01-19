package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Board;
import com.nicolemccabe.javabattleship2.BoardPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;

public class BoardPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void test1x1BoardPrints() {
        String expected_board = "      0\n  0 |_ _|";

        BoardPrinter printer = new BoardPrinter();
        Board board = new Board(1);
        printer.print(board);

        assertEquals(expected_board, outContent.toString());
    }


    @After
    public void cleanupStream() {
        System.setOut(null);
    }

}
