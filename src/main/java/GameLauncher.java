package main.java;

import java.io.IOException;

public class GameLauncher {

    public static void main(String[] args) throws IOException, InterruptedException {
        Board board = new Board(10);
        BoardFormatter formatter = new BoardFormatter();
        Ui ui = new Ui(new ConsolePrinter(), new ConsoleReceiver(), formatter);


        Game game = new Game(board, ui);
//        int rand = new Random().nextInt(5);
//        game.setShipAt(new Ship(2), rand, rand);

//        game.welcomeSequence();
//
//        while (!game.gameIsOver) {
//            game.playersTurn();
//        }

        game.startGame();

        formatter.format(board);
    }

}
