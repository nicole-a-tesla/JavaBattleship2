package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public void setupBoard() {
        Ship[] ships = board.getShips();
        ArrayList<Integer> fullSpaces = new ArrayList<>();

        for (Ship ship: ships) {
            setShipAtRandom(ship, fullSpaces);
        }
    }

    private void setShipAtRandom(Ship ship, ArrayList<Integer> fullSpaces) {
        int rand = generateUnusedInt(fullSpaces);
        fullSpaces.add(rand);
        setShipAt(ship, rand, rand);
    }

    private int generateUnusedInt(ArrayList<Integer> usedInts) {
        int rand = new Random().nextInt(9);

        if (!usedInts.contains(rand)) {
            return rand;
        } else {
            return generateUnusedInt(usedInts);
        }
    }

    public void startGame() throws IOException, InterruptedException {
        welcomeSequence();
        setupBoard();

        while (!gameIsOver) {
            playersTurn();
        }
    }

    public void welcomeSequence() throws InterruptedException {
        welcomeSequence.runWelcomeSequence();
        printer.clearScreen();
    }

    public void playersTurn() throws IOException {
        ui.printBoard(board);
        ui.requestXY();

        CoordinateSet targetCoords= getTargetCoords();
        int x = targetCoords.getXValue();
        int y = targetCoords.getYValue();

        State strikeResult = strikeBoardAt(x, y);
        reportStrikeResults(strikeResult);
        checkForGameOver();
    }

    public void checkForGameOver() {
        if (board.allSunk()) {
            gameIsOver = true;
            ui.printBoard(board);
            printer.print("You Win!\n");
        }
    }

    public CoordinateSet getTargetCoords() throws IOException {
        String userInput = ui.getUserInput();
        return new CoordinateSet(userInput);
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