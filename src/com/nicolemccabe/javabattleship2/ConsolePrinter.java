package com.nicolemccabe.javabattleship2;

public class ConsolePrinter extends Printer {
    public void print(String string){
        System.out.print(string);
    }

    public void clearScreen() {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }
}
