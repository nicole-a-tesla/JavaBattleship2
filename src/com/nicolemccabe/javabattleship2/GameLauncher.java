package com.nicolemccabe.javabattleship2;

import java.io.IOException;

public class GameLauncher {

    public static void main(String[] args) throws IOException, InterruptedException {
        BoardPrinter printer = new BoardPrinter();
        Board board = new Board(10);
        Ui ui = new Ui(new ConsolePrinter(), new ConsoleReceiver(), new BoardPrinter());


        Game game = new Game(board, ui);
        game.setShipAt(0,0);

        game.welcomeSequence();
//        game.playersTurn();

    }

}
