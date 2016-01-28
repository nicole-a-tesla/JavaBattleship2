package test.java;

import com.nicolemccabe.javabattleship2.*;
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

    @Before
    public void setup() {
        printer = new ConsolePrinter();
        mockReceiver = mock(ConsoleReceiver.class);
        board = new Board(10);
    }

    @Test
    public void printsToPrinter() {
        Printer mockPrinter = mock(ConsolePrinter.class);
        Receiver receiver = new ConsoleReceiver();
        Ui ui = new Ui(mockPrinter, receiver, boardFormatter);
        String test = "0,0";
        ui.print(test);
        verify(mockPrinter).print(test);
    }

    @Test
    public void receivesViaReceiver() throws IOException {
        Ui ui = new Ui(printer, mockReceiver, boardFormatter);
        ui.getUserInput();
        verify(mockReceiver).getUserInput();
    }

    @Test
    public void testUserInteractionOnXYRequest() throws IOException {
        Ui ui = new Ui(printer, mockReceiver, new BoardFormatter());
        when(mockReceiver.getUserInput()).thenReturn("0,0");

        Game game = new Game(board, ui);

        game.playersTurn();
        verify(mockReceiver).getUserInput();
    }

    @Test
    public void testInternalBoardPrinter() throws IOException {
        BoardFormatter mockBoardFormatter = mock(BoardFormatter.class);
        Ui ui = new Ui(printer, new ConsoleReceiver(), mockBoardFormatter);
        Game game = new Game(board, ui);

        game.playersTurn();
        verify(mockBoardFormatter).format(board);
    }

}
