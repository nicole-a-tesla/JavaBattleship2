package test.java;

import main.java.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UiTest {
    private Printer printer;
    private Receiver mockReceiver;
    private BoardFormatter boardFormatter;
    private Board board;
    private BoardPrinter boardPrinter;
    private BoardPrintManager boardPrintManager;

    @Before
    public void setup() {
        printer = new ConsolePrinter();
        mockReceiver = mock(ConsoleReceiver.class);
        board = new Board(10);
        boardPrinter = new BoardPrinter(printer);
        boardFormatter = new BoardFormatter();
        boardPrintManager = new BoardPrintManager(boardFormatter, boardPrinter);

    }

    @Test
    public void printsToPrinter() {
        Printer mockPrinter = mock(ConsolePrinter.class);
        Receiver receiver = new ConsoleReceiver();
        BoardPrintManager manager = new BoardPrintManager(new BoardFormatter(), boardPrinter);
        Ui ui = new Ui(mockPrinter, receiver, manager);
        String test = "0,0";
        ui.print(test);
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
        when(mockReceiver.getUserInput()).thenReturn("0,0");

        Game game = new Game(board, ui);

        game.playersTurn();
        verify(mockReceiver).getUserInput();
    }

    @Test
    public void testInternalBoardFormatter() throws IOException {
        BoardFormatter mockBoardFormatter = mock(BoardFormatter.class);
        BoardPrintManager manager = new BoardPrintManager(mockBoardFormatter, boardPrinter);
        Ui ui = new Ui(printer, new ConsoleReceiver(), manager);
        Game game = new Game(board, ui);

        game.playersTurn();

        verify(mockBoardFormatter).format(board);
    }

}
