package com.nicolemccabe.javabattleship2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WelcomeSequence {
    private Printer printer;

    public WelcomeSequence(Printer printer) {
        this.printer = printer;
    }

    private final String bang =  "\uD83D\uDCA5";
    private final String welcomeString =
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

    private ArrayList<String> welcomeLines =  new ArrayList<>(Arrays.asList(welcomeString.split("\n")));

    public String getWelcomeString() {
        return welcomeString;
    }

    public ArrayList<String> getWelcomeLines() {
        return welcomeLines;
    }

    public String getBang() {
        return bang;
    }

    public void runWelcomeSequence() throws InterruptedException {
        printer.clearScreen();

        for (String str:welcomeLines) {
            printer.print(str);
            printer.print("\n");
            TimeUnit.MILLISECONDS.sleep(75);
        }
        TimeUnit.MILLISECONDS.sleep(200);
        fireAtRandom(welcomeString, 0);
        TimeUnit.MILLISECONDS.sleep(300);
        printer.print("WELCOME TO BATTLESHIP\n");
        TimeUnit.SECONDS.sleep(2);
    }

    public void printNewShip(String ship) throws InterruptedException {
        printer.clearScreen();
        ArrayList<String> shipLinesArray = new ArrayList<>(Arrays.asList(ship.split("\n")));

        for (String str: shipLinesArray) {
            printer.print(str);
            printer.print("\n");
        }

        TimeUnit.MILLISECONDS.sleep(150);
    }

    private String fireAtRandom(String shipString, int count) throws InterruptedException {
        if (count == 15) {
            return shipString;

        } else {

            int n = new Random().nextInt(shipString.length() - 115) + 1;
            String charToReplace = shipString.substring(n, n + 1);

            if (isNotPartOfNewline(charToReplace)) {
                String newShipString = shipString.substring(0, n) + bang + shipString.substring(n + 1);
                printNewShip(newShipString);
                return fireAtRandom(newShipString, count + 1);
            } else {
                printNewShip(shipString);
                return fireAtRandom(shipString, count + 1);
            }
        }
    }

    public boolean isNotPartOfNewline(String character) {
        boolean goodToGo = true;

        if (character.matches("[\\n\\r]+")) {
            goodToGo = false;
        }

        return goodToGo;
    }
}
