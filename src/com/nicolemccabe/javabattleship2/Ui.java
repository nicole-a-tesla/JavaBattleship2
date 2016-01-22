package com.nicolemccabe.javabattleship2;

public class Ui {
    private Printer printer;
    private Receiver receiver;

    public Ui(Printer printer, Receiver receiver) {
        this.printer = printer;
        this.receiver = receiver;
    }
    public void requestXY() {
        printer.print("Time to shoot stuff!\n");
        printer.print("Enter your target's coordinates as 'x, y' :");
    }

    public void getUserInput() {
        receiver.getUserInput();
    }

    public void print(String string) {
        printer.print(string);
    }

}
