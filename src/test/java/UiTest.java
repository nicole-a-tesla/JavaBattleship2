package test.java;

import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UiTest {
    private Printer printer;
    private Printer mockPrinter;
    private Receiver mockReceiver;
    private Board opponentBoard;
    private Board playerBoard;
    private BoardPrinter boardPrinter;
    private BoardPrintManager boardPrintManager;
    private Ui uiWithFakePrinter;
    private ByteArrayInputStream inContent = new ByteArrayInputStream("A0".getBytes());
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        printer = new ConsolePrinter();
        mockReceiver = mock(ConsoleReceiver.class);
        opponentBoard = new Board(new Fleet(), 10);
        playerBoard = new Board(new Fleet(), 10);
        boardPrinter = new BoardPrinter(printer);
        BoardFormatter boardFormatter = new BoardFormatter();
        boardPrintManager = new BoardPrintManager(boardFormatter, boardPrinter);

        mockPrinter = mock(ConsolePrinter.class);
        uiWithFakePrinter = new Ui(mockPrinter, new ConsoleReceiver(), boardPrintManager);

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void printsToPrinter() {
        String test = "A0";
        uiWithFakePrinter.print(test);
        verify(mockPrinter).print(test);
    }

    @Test
    public void receivesViaReceiver() throws IOException {
        Ui ui = new Ui(printer, mockReceiver, boardPrintManager);
        ui.getUserInput();
        verify(mockReceiver).getUserInput();
    }

    @Test
    public void testUserInteractionOnXYRequest() throws IOException {
        Ui ui = new Ui(printer, mockReceiver, boardPrintManager);
        when(mockReceiver.getUserInput()).thenReturn("A0");

        Game game = new Game(opponentBoard, playerBoard, ui);

        game.playersTurn();
        verify(mockReceiver).getUserInput();
    }

    @Test
    public void testInternalBoardFormatter() throws IOException {

        BoardFormatter mockBoardFormatter = mock(BoardFormatter.class);
        BoardPrintManager manager = new BoardPrintManager(mockBoardFormatter, boardPrinter);
        Ui ui = new Ui(printer, new ConsoleReceiver(), manager);
        Game game = new Game(opponentBoard, playerBoard, ui);

        game.playersTurn();

        verify(mockBoardFormatter).format(opponentBoard);

    }

    @Test
    public void testInvalidInputMessage() {
        uiWithFakePrinter.invalidInputMessage();
        verify(mockPrinter).print("Invalid Input, please try again.\n");
    }

    @Test
    public void testShipPlacementPrompt() {
        Ship battleShip = new Ship("battleship", 4);
        uiWithFakePrinter.promptShipPlacement(battleShip);
        verify(mockPrinter).print("Enter coordinates for your 4 square long battleship ");
    }

    @Test
    public void testOrientationPrompt() {
        uiWithFakePrinter.promptForOrientation();
        verify(mockPrinter).print("Should that be vertical (0), or horizontal (1) ? ");
    }


    @After
    public void cleanupStream() {
        System.setIn(null);
        System.setOut(null);
    }

}
