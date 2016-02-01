package main.java;

import java.io.IOException;

public class Ui {
    private Printer printer;
    private Receiver receiver;
    private BoardPrintManager boardPrintManager;

    public Ui(Printer printer, Receiver receiver, BoardPrintManager boardPrintManager) {
        this.printer = printer;
        this.receiver = receiver;
        this.boardPrintManager = boardPrintManager;
    }

    public void requestXY() {
        printer.print("Time to shoot stuff!\n");
        printer.print("Enter your target's coordinates as 'x, y' :");
    }

    public String getUserInput() throws IOException {
        String received = receiver.getUserInput();
        return received;
    }

    public void print(String string) {
        printer.print(string);
    }

    public void printBoard(Board board) {
        boardPrintManager.formatAndPrintBoard(board);
    }

}
