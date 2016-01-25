package com.nicolemccabe.javabattleship2;

import java.io.IOException;
import java.util.Random;

public class GameLauncher {

    public static void main(String[] args) throws IOException, InterruptedException {
        Board board = new Board(10);
        Ui ui = new Ui(new ConsolePrinter(), new ConsoleReceiver(), new BoardFormatter());


        Game game = new Game(board, ui);
        int rand = new Random().nextInt(5) + 0;
        game.setShipAt(new Ship(1), rand,rand);

        game.welcomeSequence();

        while (!game.gameIsOver) {
            game.playersTurn();
        }

    }

}
