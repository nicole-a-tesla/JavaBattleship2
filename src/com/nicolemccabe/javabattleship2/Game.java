package com.nicolemccabe.javabattleship2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public Board board;

    public Game(Board board) {
        this.board = board;
    }
    public String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input;
    }

    public void playersTurn() throws IOException {
        int targetX = getTargetCoord("x");
        int targetY = getTargetCoord("y");
        strikeBoardAt(targetX, targetY);
    }

    public int getTargetCoord(String dimension) throws IOException {
        System.out.print("Enter Target's " + dimension + ":");
        return Integer.parseInt(getInput());
    }

    public void strikeBoardAt(int x, int y) {
        board.logStrikeAt(x, y);
    }

    public void setShipAt(int x, int y) {
        board.setShipAt(x, y);
    }


}