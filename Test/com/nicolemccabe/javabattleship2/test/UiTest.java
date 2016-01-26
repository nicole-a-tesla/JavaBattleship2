package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UiTest {
    private Printer printer;
    private Receiver mockReceiver;
    private Board board;
    private BoardPrintManager boardPrintManager;

    @Before
    public void setup() {
        printer = new ConsolePrinter();
        mockReceiver = mock(ConsoleReceiver.class);
        board = new Board(10);
        BoardFormatter formatter = new BoardFormatter();
        BoardPrinter boardPrinter = new BoardPrinter(printer);
        boardPrintManager = new BoardPrintManager(formatter, boardPrinter);
    }

    @Test
    public void printsToPrinter() {
        Printer mockPrinter = mock(ConsolePrinter.class);
        Receiver receiver = new ConsoleReceiver();
        Ui ui = new Ui(mockPrinter, receiver, boardPrintManager);
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
    public void testInternalBoardPrintManager() throws IOException {
        BoardPrintManager mockManager = mock(BoardPrintManager.class);
        Ui ui = new Ui(printer, new ConsoleReceiver(), mockManager);
        Game game = new Game(board, ui);

        game.playersTurn();
        verify(mockManager).formatAndPrintBoard(board);
    }

}
