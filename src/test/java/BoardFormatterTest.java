package test.java;

import main.java.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

    public class BoardFormatterTest {
        private BoardFormatter formatter = new BoardFormatter();
        private Fleet fleet;

        @Before
        public void setup() {
            fleet = new Fleet();
        }


        @Test
        public void test2x2BoardPrint() {
            ArrayList<String> expected = new ArrayList<>();
            expected.add( "   0  1\n");
            String water = " \uD83C\uDF0A ";
            expected.add("A " + water + water + "\n");
            expected.add( "B " + water + water + "\n");
            Board board = new Board(fleet, 2);
            ArrayList formatted = formatter.format(board);

            assertEquals(expected.get(0), formatted.get(0));
            assertEquals(expected.get(1), formatted.get(1));
            assertEquals(expected.get(2), formatted.get(2));
        }

        @Test
        public void testMissFormatting() {
            Board board = new Board(fleet, 1);
            board.logStrikeAt(0,0);
            ArrayList<String> formatted = formatter.format(board);
            String miss = " \uD83D\uDCA8 ";
            String expectedRow = "A " + miss + "\n";

            assertEquals(expectedRow, formatted.get(1));
        }

        @Test
        public void testHitFormatting() {
            Board board = new Board(fleet, 1);
            board.setShipAt(new Ship("test", 2), 0,0);
            board.logStrikeAt(0,0);
            ArrayList<String> formatted = formatter.format(board);
            String hit = " \uD83D\uDCA5 ";
            String expectedRow = "A " + hit + "\n";

            assertEquals(expectedRow, formatted.get(1));
        }

        @Test
        public void testSunkFormatting() {
            Board board = new Board(fleet, 1);
            board.setShipAt(new Ship("test", 1), 0,0);
            board.logStrikeAt(0,0);
            ArrayList<String> formatted = formatter.format(board);
            String sunk = " ♨️ ";
            String expectedRow = "A " + sunk + "\n";

            assertEquals(expectedRow, formatted.get(1));
        }

        @Test
        public void testOpponentShipHidden() {
            String water = StateMapper.mapState(State.WATER);
            Board opponentBoard = new OpponentBoard(fleet, 1);
            opponentBoard.setShipAt(new Ship("Lady Shipette of Shipalonia", 1), 0,0);


            ArrayList formatted = formatter.format(opponentBoard);
            String expected =  "A " + water + "\n";
            assertEquals(expected, formatted.get(1));
        }

    }
