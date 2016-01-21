package com.nicolemccabe.javabattleship2;

public class BoardPrinter {
    private final String padding = " ";

    public void clearScreen() {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }

    public void print(Board board) {
        clearScreen();

        int axisSize = board.size;

        printXAxis(axisSize);

        StringBuffer row = collectSpacesIntoRow(axisSize);

        StringBuffer formattedRows = formatRows(row, axisSize);

        System.out.print(formattedRows);
        System.out.print("");

    }

    private StringBuffer collectSpacesIntoRow(int axisSize) {
        final String space = " \uD83C\uDF0A ";

        StringBuffer row = new StringBuffer();

        for (int i=0; i<axisSize; i++)
            row.append(space);

        return row;
    }

    private StringBuffer formatRows(StringBuffer row, int axisSize) {
        StringBuffer formattedRows = new StringBuffer();

        for (int i=0; i<axisSize; i++)
            formattedRows.append(y_axis_num(i) + row + "\n");

        return formattedRows;
    }

    private String y_axis_num(int axis_number) {
        return axis_number + padding;
    }

    private void printXAxis(int size) {
        StringBuffer axis = new StringBuffer();
        axis.append(padding);

        for (int i=0; i<size; i++)
            axis.append(build_x_axis_element(i));

        System.out.print(String.valueOf(axis.append("\n")));
    }

    private String build_x_axis_element(int element_number) {
        return padding + padding + element_number;
    }

}
