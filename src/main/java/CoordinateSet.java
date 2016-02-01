package main.java;

import java.util.ArrayList;
import java.util.Arrays;

public class CoordinateSet {
    private int xValue;
    private int yValue;

    public CoordinateSet(String setString) {
        ArrayList<String> setList = new ArrayList<>(Arrays.asList(setString.split("")));

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
        return alphabet.indexOf(yVal);
    }
}
