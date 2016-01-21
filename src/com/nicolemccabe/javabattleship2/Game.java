package com.nicolemccabe.javabattleship2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List targetCoords= getTargetCoords();
        int x = (int) targetCoords.get(0);
        int y = (int) targetCoords.get(1);

        reportStrikeResults(strikeBoardAt(x, y));
    }

    public ArrayList getTargetCoords() throws IOException {
        getTargetCoordsUserInteraction();

        String xAndYString = getInput();
        ArrayList xAndYList = new ArrayList<>(Arrays.asList(xAndYString.split(",")));

        return parseEachToInt(xAndYList);
    }

    private void getTargetCoordsUserInteraction() {
        System.out.println("Time to shoot stuff!");
        System.out.print("Enter your target's coordinates as 'x, y' :");
    }

    private ArrayList parseEachToInt(ArrayList<String> list) {
        ArrayList intList = new ArrayList();

        for (String str: list) {
            int numAsInt = Integer.parseInt(str.trim());
            intList.add(numAsInt);
        }

        return intList;
    }

    public State strikeBoardAt(int x, int y) {
        return board.logStrikeAt(x, y);
    }

    public State setShipAt(int x, int y) {
        return board.setShipAt(x, y);
    }

    private void reportStrikeResults(State state) {
        String stateString = state.toString();
        System.out.println(stateString);
    }

}