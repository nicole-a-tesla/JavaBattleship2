package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinateSet {
    private int xValue;
    private int yValue;

    public CoordinateSet(String setString) {
        if (!inputIsValid(setString)) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> setList = inputAsArrayList(setString);
        removeWhitespace(setList);

        this.xValue = parseXValue(setList.get(1));
        this.yValue = parseYValue(setList.get(0));
    }

    public int getXValue() {
        return xValue;
    }

    public int getYValue() {
        return yValue;
    }

    private int parseXValue(String xVal) {
        return Integer.parseInt(xVal);
    }

    private int parseYValue(String yVal) {
        String alphabet = "ABCDEFGHIJ";
        return alphabet.indexOf(yVal);
    }

    private ArrayList<String> inputAsArrayList(String input) {
        return new ArrayList<>(Arrays.asList(input.toUpperCase().split("")));
    }

    private void removeWhitespace(ArrayList<String> userInput) {
        userInput.removeAll(Collections.singletonList(" "));
    }

    private boolean inputIsValid(String input) {
        Pattern validPattern = Pattern.compile("\\s*[A-J]\\s*[0-9]\\s*");
        Matcher matcher = validPattern.matcher(input);
        return matcher.find();
    }
}
