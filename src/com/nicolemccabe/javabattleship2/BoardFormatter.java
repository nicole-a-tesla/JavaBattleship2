package com.nicolemccabe.javabattleship2;

import java.util.ArrayList;

public class BoardFormatter {
    private Printer printer = new ConsolePrinter();
    private final String padding = " ";
    private Board board;

    public void print(Board board) {
        this.board = board;
        int axisSize = board.size;

        StringBuffer xAxis = getXAxis(axisSize);
        printer.print(String.valueOf(xAxis));

        int rowCount = 0;

        for (ArrayList rowOfSpaces : rows()) {
            StringBuffer rowOfStrings = collectSpacesIntoRow(rowOfSpaces, rowCount);
            printer.print(String.valueOf(rowOfStrings));
            rowCount++;
        }
    }

    private StringBuffer collectSpacesIntoRow(ArrayList<Space> row, int rowCount) {
        StringBuffer rowIncubator = new StringBuffer();

        rowIncubator.append(y_axis_num(rowCount));

        for (Space space: row) {
            String spaceRepresentation = " \uD83C\uDF0A ";
            rowIncubator.append(spaceRepresentation);
        }

        rowIncubator.append("\n");

        return rowIncubator;
    }

    private String y_axis_num(int axis_number) {
        return axis_number + padding;
    }

    private StringBuffer getXAxis(int size) {
        StringBuffer axis = new StringBuffer().append(padding);

        for (int i=0; i<size; i++)
            axis.append(build_x_axis_element(i));

        return axis.append("\n");
    }

    private String build_x_axis_element(int element_number) {
        return padding + padding + element_number;
    }

    private ArrayList<ArrayList> rows() {
        return board.getGrid();
    }
}