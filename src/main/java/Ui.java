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
        printer.print("Enter your target's coordinates :");
    }

    public String getUserInput() throws IOException {
        return receiver.getUserInput();
    }

    public void print(String string) {
        printer.print(string);
    }

    public void printBoard(Board board) {
        boardPrintManager.formatAndPrintBoard(board);
    }

    public void invalidInputMessage() {
        printer.print("Invalid Input, please try again.\n");
    }

    public void promptShipPlacement(Ship ship) {
        printer.print("Enter coordinates for your " + ship.getSize() + " square long " + ship.getName() + " ");
    }

    public void promptForOrientation() {
        printer.print("Should that be vertical (0), or horizontal (1) ? ");
    }


}
