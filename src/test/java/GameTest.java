package test.java;

import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    private ByteArrayInputStream inContent = new ByteArrayInputStream("A0".getBytes());
    private Game game;
    private Board opponentBoard;
    private Board playerBoard;
    private Ui ui;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);

        opponentBoard = new Board(new Fleet(), 10);
        playerBoard = new Board(new Fleet(), 10);


        Printer consolePrinter = new ConsolePrinter();
        BoardPrinter boardPrinter = new BoardPrinter(consolePrinter);
        BoardFormatter boardFormatter = new BoardFormatter();
        BoardPrintManager manager = new BoardPrintManager(boardFormatter, boardPrinter);

        ui = new Ui(new ConsolePrinter(), new ConsoleReceiver(), manager);
        game = new Game(opponentBoard, playerBoard, ui);

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
        assertEquals(opponentBoard.getStateAt(0,0), State.MISS);
    }

    @Test
    public void testPrintsBoardOnPlayerTurn() throws IOException {
        Ui mockUi = mock(Ui.class);
        when(mockUi.getUserInput()).thenReturn("A0");
        Game game = new Game(opponentBoard, playerBoard, mockUi);

        game.playersTurn();

        verify(mockUi).printBoard(opponentBoard);
    }

    @Test
    public void testGetCoordsInput() throws IOException{
        CoordinateSet coords = game.getTargetCoords();

        assertEquals(0, coords.getXValue());
        assertEquals(0, coords.getYValue());
    }

    @Test
    public void testGameOver() {
        List<Ship> ships = opponentBoard.getShips();
        Helpers.sinkThisManyShips(ships, 5);

        game.checkForGameOver();
        assertTrue(game.gameIsOver);
    }


    @Test
    public void testGameOverMessage() throws IOException {
        List<Ship> ships = opponentBoard.getShips();
        Helpers.sinkThisManyShips(ships, 5);

        game.checkForGameOver();

        String[] outputArray = outContent.toString().split("\n");
        String lastMessage = outputArray[outputArray.length - 1];

        String expected = "You Win!";
        assertEquals(expected, lastMessage);
    }

    @Test
    public void testGameOverMessageSelectivity() {
        List<Ship> ships = opponentBoard.getShips();
        Helpers.sinkThisManyShips(ships, 4);

        game.checkForGameOver();
        assertEquals("", outContent.toString());
    }

    @Test
    public void testGetOrientation() throws IOException {
        inContent = new ByteArrayInputStream("1".getBytes());
        System.setIn(inContent);

        game.getOrientation();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNonsenseOrientation() throws IOException {
        inContent = new ByteArrayInputStream("i like hats!!".getBytes());
        System.setIn(inContent);

        thrown.expect(IllegalArgumentException.class);
        game.getOrientation();
    }

    @Test
    public void testOutOfBoundsOrientation() throws IOException {
        inContent = new ByteArrayInputStream("19".getBytes());
        System.setIn(inContent);

        thrown.expect(IllegalArgumentException.class);
        game.getOrientation();
    }

    @Test
    public void testComputerTurn(){
        Board board = new Board(new Fleet(), 1);
        game.computersTurn(board);
        assertEquals(State.MISS, board.getStateAt(0,0));
    }

    @Test
    public void testComputerTurnOnLargerBoard() {
        Board board = new Board(new Fleet(), 2);

        for (int i=0; i<50; i++) {
            game.computersTurn(board);
        }

        assertEquals(State.MISS, board.getStateAt(0,0));
        assertEquals(State.MISS, board.getStateAt(0,1));
        assertEquals(State.MISS, board.getStateAt(1,0));
        assertEquals(State.MISS, board.getStateAt(1,1));

    }

    @Test
    public void testComputerCanWin() {
        Ship ship = new Ship("Sir Shipsalot", 1);
        List<Ship> ships = new ArrayList<>();
        ships.add(ship);

        Fleet mockFleet = mock(Fleet.class);
        when(mockFleet.getShips()).thenReturn(ships);

        Board board = new Board(mockFleet, 1);
        board.setShipAt(ship, 0,0);

        Game g = new Game(new Board(new Fleet(), 1), board, ui);
        g.computersTurn(board);

        g.checkForGameOver();
        assertTrue(g.gameIsOver);

        String[] printed = outContent.toString().split("\n");
        assertEquals("You Lose!", printed[printed.length - 1]);

    }

    @After
    public void cleanupStream() {
        System.setOut(null);
        System.setIn(null);
    }
}
