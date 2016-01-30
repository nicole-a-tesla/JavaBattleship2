package test.java;

import main.java.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoardPrintManagerTest {
    @Test
    public void sendsBoardToFormatter() {
        BoardFormatter mockFormatter = mock(BoardFormatter.class);
        Printer consolePrinter = new ConsolePrinter();
        BoardPrinter boardPrinter = new BoardPrinter(consolePrinter);

        BoardPrintManager manager = new BoardPrintManager(mockFormatter, boardPrinter);

        Board board = new Board(10);
        manager.formatAndPrintBoard(board);

        
        verify(mockFormatter).format(board);
    }

    @Test
    public void sendsFormattedBoardToPrinter() {
        BoardFormatter formatter = new BoardFormatter();


        BoardPrinter mockPrinter = mock(BoardPrinter.class);

        BoardPrintManager manager = new BoardPrintManager(formatter, mockPrinter);

        Board board = new Board(10);
        manager.formatAndPrintBoard(board);

        ArrayList formatted = formatter.format(board);

        verify(mockPrinter).printBoard(formatted);

    }
}
