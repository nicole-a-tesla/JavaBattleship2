package main.java;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

    public void setupOpponentBoard() {
        opponentBoard.setAllShipsAtRandom();
    }

    public void startGame() throws IOException, InterruptedException, IllegalArgumentException {
        welcomeSequence();
        setupOpponentBoard();
        setupPlayerBoard();

        while (!gameIsOver) {
            playersTurn();
        }
    }

    public void setupPlayerBoard() throws IOException, IllegalArgumentException {
        List<Ship> ships = playerBoard.getShips();

        for (Ship ship: ships) {
            ui.printBoard(playerBoard);
            setupShip(ship);
        }
    }

    private void setupShip(Ship ship) throws IOException, IllegalArgumentException {
        ui.promptShipPlacement(ship);
        CoordinateSet coords = getTargetCoords();
        String orientation = getOrientation();

        ShipSetArgs args = new ShipSetArgs(ship, coords.getXValue(), coords.getYValue(), orientation);

        if (!playerBoard.attemptSet(args, 0)) {
            ui.invalidInputMessage();
            setupShip(ship);
        }

    }

    public String getOrientation() throws IOException, IllegalArgumentException {
        ui.promptForOrientation();
        String userInput = ui.getUserInput();
        validateOrientationChoice(userInput);

        String[] options = {"vertical", "horizontal"};

        try {
            return options[Integer.parseInt(userInput)];
        } catch (NullPointerException e) {
            ui.invalidInputMessage();
            return getOrientation();
        }

    }

    private void validateOrientationChoice(String choice) throws IllegalArgumentException {
        if (!Objects.equals(choice, "0") && !Objects.equals(choice, "1")) {
            throw new IllegalArgumentException();
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