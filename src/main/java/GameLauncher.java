package main.java;

import java.io.IOException;

public class GameLauncher {

    public static void main(String[] args) throws IOException, InterruptedException {
        Board board = new Board(10);
        BoardFormatter formatter = new BoardFormatter();
        Printer consolePrinter = new ConsolePrinter();
        BoardPrinter boardPrinter = new BoardPrinter(consolePrinter);
        BoardPrintManager manager = new BoardPrintManager(formatter, boardPrinter);

        Ui ui = new Ui(new ConsolePrinter(), new ConsoleReceiver(), manager);
        Game game = new Game(board, ui);

        game.startGame();

    }

}
