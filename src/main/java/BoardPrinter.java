package main.java;

import java.util.ArrayList;

public class BoardPrinter {
    private Printer printer;

    public BoardPrinter(Printer printer) {
        this.printer = printer;
    }

    public void printBoard(ArrayList<String> formattedBoard) {
        printer.print("\n");
        for (String row: formattedBoard) {
            printRow(row);
        }
    }

    private void printRow(String row) {
        printer.print(row);
    }

}
