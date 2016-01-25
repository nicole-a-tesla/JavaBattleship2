package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Board;
import com.nicolemccabe.javabattleship2.BoardFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

    public class BoardFormatterTest {
        private String water = " \uD83C\uDF0A ";
        private String miss  = " \uD83D\uDCA8 ";
        private String screenClearer = "\u001B[2J\u001B[H";
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private BoardFormatter printer = new BoardFormatter();

        @Before
        public void setup() {
            System.setOut(new PrintStream(outContent));
        }

        @Test
        public void test2x2BoardPrint() {
            String expected_board = "   0  1\n0 " + water + water + "\n1 " + water + water + "\n";
            Board board = new Board(2);
            printer.print(board);

            assertEquals(expected_board, outContent.toString());
        }

        @Test
        public void test3x3BoardPrint() {
            String expected_board = "   0  1  2\n0 " + water + water + water + "\n1 " + water + water + water + "\n2 " + water + water + water + "\n";
            Board board = new Board(3);
            printer.print(board);

            assertEquals(expected_board, outContent.toString());
        }

        @Test
        public void testMissFormatting() {
            String expectedBoard = "   0  1\n0 " + miss + water + "\n1 " + water + water + "\n";
            Board board = new Board(2);
            board.logStrikeAt(0,0);
            printer.print(board);

            assertEquals(expectedBoard, outContent.toString());
        }

        @After
        public void cleanupStream() {
            System.setOut(null);
        }

    }
