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
        private String space = " \uD83C\uDF0A ";
        private String screenClearer = "\u001B[2J\u001B[H";
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private BoardFormatter printer = new BoardFormatter();

        @Before
        public void setup() {
            System.setOut(new PrintStream(outContent));
        }

        @Test
        public void test2x2BoardPrint() {
            String expected_board = screenClearer + "   0  1\n0 " + space + space + "\n1 " + space + space + "\n";
            Board board = new Board(2);
            printer.print(board);

            assertEquals(expected_board, outContent.toString());
        }

        @Test
        public void test3x3BoardPrint() {
            String expected_board = screenClearer + "   0  1  2\n0 " + space + space + space + "\n1 " + space + space + space + "\n2 " + space + space + space + "\n";
            Board board = new Board(3);
            printer.print(board);

            assertEquals(expected_board, outContent.toString());
        }

        @After
        public void cleanupStream() {
            System.setOut(null);
        }

    }
