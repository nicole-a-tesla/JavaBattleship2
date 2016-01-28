package test.java;

import main.java.Board;
import main.java.BoardFormatter;
import main.java.Ship;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

    public class BoardFormatterTest {
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private BoardFormatter formatter = new BoardFormatter();

        @Before
        public void setup() {
            System.setOut(new PrintStream(outContent));
        }

        @Test
        public void test2x2BoardPrint() {
            ArrayList<String> expected = new ArrayList<>();
            expected.add( "   0  1\n");
            String water = " \uD83C\uDF0A ";
            expected.add("0 " + water + water + "\n");
            expected.add( "1 " + water + water + "\n");
            Board board = new Board(2);
            ArrayList formatted = formatter.format(board);

            assertEquals(expected.get(0), formatted.get(0));
            assertEquals(expected.get(1), formatted.get(1));
            assertEquals(expected.get(2), formatted.get(2));
        }

        @Test
        public void testMissFormatting() {
            Board board = new Board(1);
            board.logStrikeAt(0,0);
            ArrayList<String> formatted = formatter.format(board);
            String miss = " \uD83D\uDCA8 ";
            String expectedRow = "0 " + miss + "\n";

            assertEquals(expectedRow, formatted.get(1));
        }

        @Test
        public void testHitFormatting() {
            Board board = new Board(1);
            board.setShipAt(new Ship(2), 0,0);
            board.logStrikeAt(0,0);
            ArrayList<String> formatted = formatter.format(board);
            String hit = " \uD83D\uDCA5 ";
            String expectedRow = "0 " + hit + "\n";

            assertEquals(expectedRow, formatted.get(1));
        }

        @Test
        public void testSunkFormatting() {
            Board board = new Board(1);
            board.setShipAt(new Ship(1), 0,0);
            board.logStrikeAt(0,0);
            ArrayList<String> formatted = formatter.format(board);
            String sunk = " ♨️ ";
            String expectedRow = "0 " + sunk + "\n";

            assertEquals(expectedRow, formatted.get(1));
        }


    }
