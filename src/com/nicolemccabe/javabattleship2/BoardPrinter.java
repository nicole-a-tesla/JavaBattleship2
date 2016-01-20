package com.nicolemccabe.javabattleship2;

public class BoardPrinter {
    private final String space = "|_~_";
    private final String doubleWhitespace = "  ";
    private final String singleWhitespace = " ";

    public void print(Board board) {
        int axisSize = board.size;

        printXAxis(axisSize);

        StringBuffer row = new StringBuffer();

        for (int i=0; i<axisSize; i++)
            row.append(space);

        for (int i=0; i<axisSize; i++) {
            String finalRow = y_axis_num(i) + row + "\n";
            System.out.print(finalRow);
        }

        System.out.print("");

    }

    private String y_axis_num(int axis_number) {
        return axis_number + singleWhitespace;
    }

    private void printXAxis(int size) {
        StringBuffer axis = new StringBuffer();
        axis.append(doubleWhitespace);

        for (int i=0; i<size; i++)
            axis.append(build_x_axis_element(i));

        System.out.print(String.valueOf(axis.append("\n")));
    }

    private String build_x_axis_element(int element_number) {
        return doubleWhitespace + element_number + singleWhitespace;
    }

}
