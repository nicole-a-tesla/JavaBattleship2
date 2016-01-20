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

    public State getStateAt(int x, int y) {
        Space space = getSpaceAt(x, y);
        return space.getState();
    }

    public void logStrikeAt(int x, int y) {
        Space space = getSpaceAt(x, y);
        space.logStrike();
    }

    private Space getSpaceAt(int x, int y) {
        ArrayList<Space> row = (ArrayList<Space>) grid.get(y);
        return row.get(x);
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

    public void setShipAt(int x, int y) {
        Space space = getSpaceAt(x, y);
        space.setShip();
    }

}
