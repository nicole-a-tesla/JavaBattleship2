package com.nicolemccabe.javabattleship2;

import java.io.IOException;

public class GameLauncher {

    public static void main(String[] args) throws IOException {
        BoardPrinter printer = new BoardPrinter();
        Board board = new Board(10);
        printer.print(board);

        Game game = new Game(board);
        game.playersTurn();

    }

}
