package test.java;

import main.java.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("0, 0".getBytes());
    private Game game;
    private Board board;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);

        board = new Board(10);
        Ui ui = new Ui(new ConsolePrinter(), new ConsoleReceiver(), new BoardFormatter());
        game = new Game(board, ui);

    }

    @Test
    public void testStrikesBoardAtCoordsAndReturnsState() {
        State resultingState = game.strikeBoardAt(0,0);
        assertEquals(State.MISS, resultingState);
    }

    @Test
    public void testGameSetsShipAndReturnsState() {
        State resultingState = game.setShipAt(new Ship(1), 0,0);
        assertEquals(State.SHIP, resultingState);
    }

    @Test
    public void testInputMapsToStruckShip() throws IOException {
        game.playersTurn();
        assertEquals(board.getStateAt(0,0), State.MISS);
    }

    @Test
    public void testPrintsBoardOnPlayerTurn() throws IOException {
        Ui mockUi = mock(Ui.class);
        when(mockUi.getUserInput()).thenReturn("0,0");
        Game game = new Game(board, mockUi);

        game.playersTurn();

        verify(mockUi).printBoard(board);
    }

    @Test
    public void testGetCoordsInput() throws IOException{
        ArrayList coords = game.getTargetCoords();
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(0);

        assertEquals(expected.get(1), coords.get(1));
        assertEquals(expected.get(0), coords.get(0));
    }


    public void setAndStrikeShips(Game game, Board board, int numOfShips) {
        Ship[] ships = board.getShips();

        for (int i=0; i<numOfShips; i++) {
            board.setShipAt(ships[i], i, i);
            game.strikeBoardAt(i,i);
        }
    }

    @Test
    public void testGameOver() {
        setAndStrikeShips(game, board, 5);

        game.checkForGameOver();
        assertTrue(game.gameIsOver);
    }

    @Test
    public void testParseUserInput() {
        ArrayList<String> input = new ArrayList<>();
        input.add("0");

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);

        ArrayList actual = game.parseEachToInt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void testGameOverMessage() throws IOException {
        setAndStrikeShips(game, board, 5);
        game.checkForGameOver();
        String gameOverMessage = "You Win!";
        assertEquals(gameOverMessage, outContent.toString());
    }

    @Test
    public void testGameOverMessageSelectivity() {
        setAndStrikeShips(game, board, 4);
        game.checkForGameOver();
        assertEquals("", outContent.toString());
    }

    @Test
    public void testBoardSetup() {
        game.setupBoard();
        int numOfShipsSet = 0;

        for (int x=0; x<10; x++) {
            for (int y=0; y<10; y++) {
                if (board.getStateAt(x,y) == State.SHIP) {
                    numOfShipsSet++;
                }
            }
        }

        assertEquals(5, numOfShipsSet);

    }

}
