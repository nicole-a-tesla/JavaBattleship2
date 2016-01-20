package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Board;
import com.nicolemccabe.javabattleship2.BoardPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

    public class BoardPrinterTest {
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private BoardPrinter printer = new BoardPrinter();

        @Before
        public void setup() {
            System.setOut(new PrintStream(outContent));
        }

        @Test
        public void test2x2BoardPrint() {
            String expected_board = "    0   1 \n0 |_~_|_~_\n1 |_~_|_~_\n";
            Board board = new Board(2);
            printer.print(board);

            assertEquals(expected_board, outContent.toString());
        }

        @Test
        public void test3x3BoardPrint() {
            String expected_board = "    0   1   2 \n0 |_~_|_~_|_~_\n1 |_~_|_~_|_~_\n2 |_~_|_~_|_~_\n";
            Board board = new Board(3);
            printer.print(board);

            assertEquals(expected_board, outContent.toString());
        }

        @After
        public void cleanupStream() {
            System.setOut(null);
        }

    }
