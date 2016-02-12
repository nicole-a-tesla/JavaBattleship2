package main.java;

import java.util.ArrayList;

public class BoardFormatter {
    private final String padding = " ";
    private Board board;
    private String[] yAxisMap = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public ArrayList<String> format(Board board) {
        this.board = board;

        ArrayList<String> formatted = new ArrayList<>();

        StringBuffer xAxis = getXAxis(board.getSize());
        formatted.add(String.valueOf(xAxis));

        int rowCount = 0;

        for (ArrayList rowOfSpaces : rows()) {
            StringBuffer rowOfStrings = buildRow(rowOfSpaces, rowCount);
            formatted.add(String.valueOf(rowOfStrings));
            rowCount++;
        }
        return formatted;
    }

    private StringBuffer buildRow(ArrayList<Space> row, int rowCount) {
        StringBuffer rowIncubator = new StringBuffer();

        rowIncubator.append(y_axis_num(rowCount));

        for (Space space: row) {
            State state = space.getState();
            String spaceRepresentation = getSpaceRepresentation(state);
            rowIncubator.append(spaceRepresentation);
        }

        rowIncubator.append("\n");

        return rowIncubator;
    }

    private String getSpaceRepresentation(State state) {
        if (board.getClass() == OpponentBoard.class && state == State.SHIP) {
            return StateMapper.mapState(State.WATER);
        } else {
            return StateMapper.mapState(state);
        }
    }

    private String y_axis_num(int axis_number) {
        return yAxisMap[axis_number] + padding;
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
