package com.nicolemccabe.javabattleship2;

import java.io.IOException;

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

    public String getUserInput() throws IOException {
        String received = receiver.getUserInput();
        return received;
    }

    public void print(String string) {
        printer.print(string);
    }

}
