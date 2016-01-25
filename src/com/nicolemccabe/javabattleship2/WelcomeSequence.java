package com.nicolemccabe.javabattleship2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WelcomeSequence {
    private Printer printer;
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

    private ArrayList<String> welcomeLines =  splitAtNewlines(welcomeString);

    public WelcomeSequence(Printer printer) {
        this.printer = printer;
    }

    public void runWelcomeSequence() throws InterruptedException {
        printer.clearScreen();

        for (String str:welcomeLines) {
            printer.print(str + "\n");
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

        ArrayList<String> newShipLines = splitAtNewlines(ship);

        for (String str: newShipLines) {
            printer.print(str + "\n");
        }

        TimeUnit.MILLISECONDS.sleep(150);
    }

    private String fireAtRandom(String shipString, int count) throws InterruptedException {
        if (count == 15) {
            return shipString;
        } else {

            int rand = new Random().nextInt(shipString.length() - 115) + 1;
            String charToReplace = shipString.substring(rand, rand + 1);

            if (isNotPartOfNewline(charToReplace)) {
                String newShipString = addBangToString(shipString, rand);
                printNewShip(newShipString);
                return fireAtRandom(newShipString, count + 1);
            } else {
                printNewShip(shipString);
                return fireAtRandom(shipString, count + 1);
            }
        }
    }

    private String addBangToString(String shipString, int replacementIndex) {
        String bang =  "\uD83D\uDCA5";
        return shipString.substring(0, replacementIndex) + bang + shipString.substring(replacementIndex + 1);
    }

    private ArrayList<String> splitAtNewlines(String string) {
        return new ArrayList<>(Arrays.asList(string.split("\n")));
    }

    public boolean isNotPartOfNewline(String character) {
        boolean goodToGo = true;

        if (character.matches("[\\n]+")) {
            goodToGo = false;
        }

        return goodToGo;
    }
}
