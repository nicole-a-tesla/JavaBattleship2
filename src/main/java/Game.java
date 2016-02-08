package main.java;

import java.io.IOException;

public class Game {
    public Board opponentBoard;
    public Board playerBoard;
    public Ui ui;
    public Printer printer = new ConsolePrinter();
    private WelcomeSequence welcomeSequence = new WelcomeSequence(printer);
    public boolean gameIsOver = false;

    public Game(Board opponentBoard, Board playerBoard, Ui ui) {
        this.opponentBoard = opponentBoard;
        this.playerBoard = playerBoard;
        this.ui = ui;
    }

    public void setupBoard() {
        opponentBoard.setAllShipsAtRandom();
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
        ui.printBoard(playerBoard);
        ui.printBoard(opponentBoard);
        ui.requestXY();

        CoordinateSet targetCoords= getTargetCoords();
        int x = targetCoords.getXValue();
        int y = targetCoords.getYValue();

        State strikeResult = strikeBoardAt(x, y);
        reportStrikeResults(strikeResult);
        checkForGameOver();
    }

    public void checkForGameOver() {
        if (opponentBoard.allSunk()) {
            gameIsOver = true;
            ui.printBoard(opponentBoard);
            printer.print("You Win!\n");
        }
    }

    public CoordinateSet getTargetCoords() throws IOException {
        String userInput = ui.getUserInput();
        try {
            return new CoordinateSet(userInput);
        } catch (IllegalArgumentException e) {
            ui.invalidInputMessage();
            return getTargetCoords();
        }
    }

    public State strikeBoardAt(int x, int y) {
        return opponentBoard.logStrikeAt(x, y);
    }

    public State setShipAt(Ship ship, int x, int y) {
        return opponentBoard.setShipAt(ship, x, y);
    }

    private void reportStrikeResults(State state) {
        String stateString = state.toString();
        System.out.println(stateString);
    }


}