package main.java;

import java.io.IOException;

public class GameLauncher {

    public static void main(String[] args) throws IOException, InterruptedException {
        Printer consolePrinter = new ConsolePrinter();
        BoardPrintManager manager = new BoardPrintManager(new BoardFormatter(), new BoardPrinter(consolePrinter));
        Ui ui = new Ui(consolePrinter, new ConsoleReceiver(), manager);
        Game game = new Game(new Board(10), ui);

        game.startGame();

    }

}
