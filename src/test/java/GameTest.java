package test.java;

import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("A0".getBytes());
    private Game game;
    private Board board;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);

        board = new Board(new Fleet(), 10);

        Printer consolePrinter = new ConsolePrinter();
        BoardPrinter boardPrinter = new BoardPrinter(consolePrinter);
        BoardFormatter boardFormatter = new BoardFormatter();
        BoardPrintManager manager = new BoardPrintManager(boardFormatter, boardPrinter);

        Ui ui = new Ui(new ConsolePrinter(), new ConsoleReceiver(), manager);
        game = new Game(board, ui);

    }

    @Test
    public void testStrikesBoardAtCoordsAndReturnsState() {
        State resultingState = game.strikeBoardAt(0,0);
        assertEquals(State.MISS, resultingState);
    }

    @Test
    public void testGameSetsShipAndReturnsState() {
        State resultingState = game.setShipAt(new Ship("test", 1), 0,0);
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
        when(mockUi.getUserInput()).thenReturn("A0");
        Game game = new Game(board, mockUi);

        game.playersTurn();

        verify(mockUi).printBoard(board);
    }

    @Test
    public void testGetCoordsInput() throws IOException{
        CoordinateSet coords = game.getTargetCoords();

        assertEquals(0, coords.getXValue());
        assertEquals(0, coords.getYValue());
    }

    @Test
    public void testGameOver() {
        List<Ship> ships = board.getShips();
        Helpers.sinkThisManyShips(ships, 5);

        game.checkForGameOver();
        assertTrue(game.gameIsOver);
    }


    @Test
    public void testGameOverMessage() throws IOException {
        List<Ship> ships = board.getShips();
        Helpers.sinkThisManyShips(ships, 5);

        game.checkForGameOver();

        String[] outputArray = outContent.toString().split("\n");
        String lastMessage = outputArray[outputArray.length - 1];

        String expected = "You Win!";
        assertEquals(expected, lastMessage);
    }

    @Test
    public void testGameOverMessageSelectivity() {
        List<Ship> ships = board.getShips();
        Helpers.sinkThisManyShips(ships, 4);

        game.checkForGameOver();
        assertEquals("", outContent.toString());
    }


    @After
    public void cleanupStream() {
        System.setOut(null);
        System.setIn(null);
    }
}
