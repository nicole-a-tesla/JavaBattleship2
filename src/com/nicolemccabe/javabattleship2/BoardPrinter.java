package com.nicolemccabe.javabattleship2;

import java.util.ArrayList;

public class BoardPrinter {
    private Printer printer;

    public BoardPrinter(Printer printer) {
        this.printer = printer;
    }

    public void printBoard(ArrayList<String> formattedBoard) {
        for (String row: formattedBoard) {
            printRow(row);
        }
    }

    private void printRow(String row) {
        printer.print(row);
    }

}
