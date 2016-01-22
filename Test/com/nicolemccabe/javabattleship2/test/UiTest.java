package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UiTest {
    private Printer printer;
    private Receiver mockReceiver;
    private BoardPrinter boardPrinter;
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
        Ui ui = new Ui(mockPrinter, receiver, boardPrinter);
        String test = "0,0";
        ui.print(test);
        verify(mockPrinter).print(test);
    }

    @Test
    public void receivesViaReceiver() throws IOException {
        Ui ui = new Ui(printer, mockReceiver, boardPrinter);
        ui.getUserInput();
        verify(mockReceiver).getUserInput();
    }

    @Test
    public void testUserInteractionOnXYRequest() throws IOException {
        Ui ui = new Ui(printer, mockReceiver, new BoardPrinter());
        when(mockReceiver.getUserInput()).thenReturn("0,0");

        Game game = new Game(board, ui);

        game.playersTurn();
        verify(mockReceiver).getUserInput();
    }

    @Test
    public void testInternalBoardPrinter() throws IOException {
        BoardPrinter mockBoardPrinter = mock(BoardPrinter.class);
        Ui ui = new Ui(printer, new ConsoleReceiver(), mockBoardPrinter);
        Game game = new Game(board, ui);

        game.playersTurn();
        verify(mockBoardPrinter).print(board);
    }

}
