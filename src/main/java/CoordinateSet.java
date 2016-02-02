package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CoordinateSet {
    private int xValue;
    private int yValue;

    public CoordinateSet(String setString) {
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
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return alphabet.indexOf(yVal.toUpperCase());
    }

    private ArrayList<String> inputAsArrayList(String input) {
        return new ArrayList<>(Arrays.asList(input.split("")));
    }

    private void removeWhitespace(ArrayList<String> userInput) {
        userInput.removeAll(Collections.singletonList(" "));
    }
}
