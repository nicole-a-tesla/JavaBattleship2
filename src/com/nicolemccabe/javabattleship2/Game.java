package com.nicolemccabe.javabattleship2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    public Board board;
    public Ui ui;
    public Printer printer = new ConsolePrinter();
    private WelcomeSequence welcomeSequence = new WelcomeSequence(printer);
    public boolean gameIsOver = false;

    public Game(Board board, Ui ui) {
        this.board = board;
        this.ui = ui;
    }

    public void welcomeSequence() throws InterruptedException {
        welcomeSequence.runWelcomeSequence();
        printer.clearScreen();
    }


    public void playersTurn() throws IOException {
        ui.printBoard(board);
        ui.requestXY();

        List targetCoords= getTargetCoords();
        int x = (int) targetCoords.get(0);
        int y = (int) targetCoords.get(1);

        State strikeResult = strikeBoardAt(x, y);
        reportStrikeResults(strikeResult);
        checkForGameOver(strikeResult);
    }

    public void checkForGameOver(State strikeResult) {
        if (strikeResult == State.SUNK) {
            gameIsOver = true;
        }
    }

    public ArrayList getTargetCoords() throws IOException {
        String xAndYString = ui.getUserInput();
        ArrayList<String> xAndYList = new ArrayList<>(Arrays.asList(xAndYString.split(",")));

        return parseEachToInt(xAndYList);
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

    public State setShipAt(Ship ship, int x, int y) {
        return board.setShipAt(ship, x, y);
    }

    private void reportStrikeResults(State state) {
        String stateString = state.toString();
        System.out.println(stateString);
    }


}