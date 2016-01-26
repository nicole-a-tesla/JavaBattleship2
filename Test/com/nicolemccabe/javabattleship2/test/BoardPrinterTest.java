package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Board;
import com.nicolemccabe.javabattleship2.BoardFormatter;
import com.nicolemccabe.javabattleship2.BoardPrinter;
import com.nicolemccabe.javabattleship2.ConsolePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BoardPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void test() {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        BoardPrinter boardPrinter = new BoardPrinter(consolePrinter);
        BoardFormatter boardFormatter = new BoardFormatter();
        Board board = new Board(2);

        ArrayList<String> formattedBoard = boardFormatter.format(board);
        boardPrinter.printBoard(formattedBoard);

        String water = " \uD83C\uDF0A ";
        String expectedOutput = "   0  1\n0 " + water + water + "\n1 " + water + water + "\n";

        assertEquals(expectedOutput, outContent.toString());



    }

    @After
    public void cleanupStream() {
        System.setOut(null);
    }
}
