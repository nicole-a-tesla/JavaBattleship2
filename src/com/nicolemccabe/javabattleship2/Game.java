package com.nicolemccabe.javabattleship2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game {
    public Board board;
    public Ui ui;
    public Printer printer = new ConsolePrinter();
    private String welcomeString =
            "                                     |__\n" +
            "                                     |\\/\n" +
            "                                     ---\n" +
            "                                     / | [\n" +
            "                              !      | |||\n" +
            "                            _/|     _/|-++'\n" +
            "                        +  +--|    |--|--|_ |-\n" +
            "                     { /|__|  |/\\__|  |--- |||__/\n" +
            "                    +---------------___[}-_===_.'____                 /\\\n" +
            "                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _\n" +
            " __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7\n" +
            "|                                                                          /\n" +
            " \\_________________________________________________________________________|\n" +
            " \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A ";
    private ArrayList<String> battleShipWelcome = new ArrayList<>(Arrays.asList(welcomeString.split("\n")));
    private String bang = "\uD83D\uDCA5";

    public Game(Board board, Ui ui) {
        this.board = board;
        this.ui = ui;
    }

    public void welcomeSequence() throws InterruptedException {
        printer.clearScreen();

        for (String str:battleShipWelcome) {
            ui.print(str);
            ui.print("\n");
            TimeUnit.MILLISECONDS.sleep(75);
        }
        TimeUnit.MILLISECONDS.sleep(200);
        fireAtRandom(welcomeString, 0);

    }

    public void printNewShip(String ship) throws InterruptedException {
        printer.clearScreen();
        ArrayList<String> shipLinesArray = new ArrayList<>(Arrays.asList(ship.split("\n")));

        for (String str: shipLinesArray) {
            ui.print(str);
            ui.print("\n");
        }

        TimeUnit.MILLISECONDS.sleep(100);

    }

    private String fireAtRandom(String shipString, int count) throws InterruptedException {
        if (count == 20) {
            return shipString;
        } else {

            Random rand = new Random();
            int  n = rand.nextInt(shipString.length()) + 1;
//            if (shipString. != "n" && shipString.charAt(n+1) != "n") {

            String newShipString = shipString.substring(0, n) + bang + shipString.substring(n+1);
            printNewShip(newShipString);

            return fireAtRandom(newShipString, count + 1);
        }

    }

    public void playersTurn() throws IOException {
        ui.printBoard(board);
        ui.requestXY();

        List targetCoords= getTargetCoords();
        int x = (int) targetCoords.get(0);
        int y = (int) targetCoords.get(1);

        reportStrikeResults(strikeBoardAt(x, y));
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

    public State setShipAt(int x, int y) {
        return board.setShipAt(x, y);
    }

    private void reportStrikeResults(State state) {
        String stateString = state.toString();
        System.out.println(stateString);
    }

}