package com.nicolemccabe.javabattleship2;

import java.util.ArrayList;

public class BoardPrintManager {
    private BoardFormatter formatter;
    private BoardPrinter boardPrinter;

    public BoardPrintManager(BoardFormatter formatter, BoardPrinter boardPrinter) {
        this.formatter = formatter;
        this.boardPrinter = boardPrinter;
    }

    public void formatAndPrintBoard(Board board) {
        ArrayList<String> formatted = formatter.format(board);
        boardPrinter.printBoard(formatted);
    }
}
