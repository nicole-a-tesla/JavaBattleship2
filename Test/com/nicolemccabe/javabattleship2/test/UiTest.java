package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.*;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UiTest {
    @Test
    public void printsToPrinter() {
        Printer mockPrinter = mock(ConsolePrinter.class);
        Receiver receiver = new ConsoleReceiver();
        Ui ui = new Ui(mockPrinter, receiver);
        String test = "0,0";
        ui.print(test);
        verify(mockPrinter).print(test);
    }

    @Test
    public void receivesViaReceiver() throws IOException {
        Printer printer = new ConsolePrinter();
        Receiver mockReceiver = mock(ConsoleReceiver.class);
        Ui ui = new Ui(printer, mockReceiver);
        ui.getUserInput();
        verify(mockReceiver).getUserInput();
    }

    @Test
    public void testUserInteractionOnXYRequest() throws IOException {
        Printer printer = new ConsolePrinter();
        Receiver mockReceiver = mock(ConsoleReceiver.class);

        Ui ui = new Ui(printer, mockReceiver);
        when(mockReceiver.getUserInput()).thenReturn("0,0");

        Board board = new Board(10);
        Game game = new Game(board, ui);

        game.playersTurn();
        verify(mockReceiver).getUserInput();
    }

}
