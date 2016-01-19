package com.nicolemccabe.javabattleship2;

public class BoardPrinter {
    private final String space = "|_ _";

    public void print(Board board) {
        int axis_size;

        if (board.size == 1) {
            axis_size = board.size;
        } else {
            axis_size = board.size / 2;
        }

        System.out.print(x_axis(axis_size));

        StringBuffer row = new StringBuffer();

        for (int i=0; i<axis_size; i++) {
            row.append(space);
        }

        for (int i=0; i<axis_size; i++) {
            String finalRow = y_axis_num(i) + row + "\n";
            System.out.print(String.valueOf(finalRow));
        }

        System.out.print("");

    }

    private String y_axis_num(int axis_number) {
        return axis_number + " ";
    }

    private String x_axis(int size) {
        StringBuffer axis = new StringBuffer();
        String padding = "  ";
        axis.append(padding);

        for (int i=0; i<size; i++) {
            axis.append(build_x_axis_element(i));
        }
        return String.valueOf(axis.append("\n"));
    }

    private String build_x_axis_element(int element_number) {
        String front_padding = "  ";
        String back_padding = " ";

        return front_padding + element_number + back_padding;
    }

    // FIRST print x axis
    // for each row
        // print y axis + space_string * board.size

}
