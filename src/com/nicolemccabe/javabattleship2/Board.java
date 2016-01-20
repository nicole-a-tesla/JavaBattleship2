package com.nicolemccabe.javabattleship2;

import java.util.ArrayList;

public class Board {
    public int size;
    private ArrayList grid;

    public Board(int size) {
        this.size = size;
        this.grid = buildGrid();
    }

    public ArrayList getGrid() {
        return grid;
    }

    private ArrayList buildGrid() {
        ArrayList localGrid = new ArrayList();

        for (int i=0;i<size;i++) {
            localGrid.add(buildRow());
        }
        return localGrid;
    }

    private ArrayList buildRow() {
        ArrayList row = new ArrayList();

        for (int i = 0; i < size; i++)
            row.add(new Space());

        return row;
    }

}
