package main.java;

import java.io.IOException;

public class GameLauncher {

    public static void main(String[] args) throws IOException, InterruptedException {
        Printer consolePrinter = new ConsolePrinter();
        BoardPrintManager manager = new BoardPrintManager(new BoardFormatter(), new BoardPrinter(consolePrinter));
        Ui ui = new Ui(consolePrinter, new ConsoleReceiver(), manager);

        Board opponentBoard = new Board(new Fleet(), 10);
        Board playerBoard = new Board(new Fleet(), 10);

        Game game = new Game(opponentBoard, playerBoard, ui);

        game.startGame();

    }

}
