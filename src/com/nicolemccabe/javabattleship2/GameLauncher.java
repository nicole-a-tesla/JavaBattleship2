package com.nicolemccabe.javabattleship2;

import java.io.IOException;

public class GameLauncher {

    public static void main(String[] args) throws IOException, InterruptedException {
        Board board = new Board(10);
        Ui ui = new Ui(new ConsolePrinter(), new ConsoleReceiver(), new BoardFormatter());


        Game game = new Game(board, ui);
        game.setShipAt(new Ship(1), 0,0);

        game.welcomeSequence();
        game.playersTurn();

    }

}
