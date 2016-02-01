package main.java;

import java.util.ArrayList;

public class Board {
    public int size;
    private ArrayList grid;
    private Ship[] ships = new Ship[5];

    public Board(int size) {
        this.size = size;
        this.grid = buildGrid();
    }

    public Ship[] getShips() {
        return ships;
    }

    public ArrayList getGrid() {
        return grid;
    }

    public State getStateAt(int x, int y) {
        Space space = getSpaceAt(x, y);
        return space.getState();
    }

    public State logStrikeAt(int x, int y) {
        Space space = getSpaceAt(x, y);
        space.logStrike();
        return getStateAt(x, y);
    }

    public State setShipAt(Ship ship, int x, int y) {
        Space space = getSpaceAt(x, y);
        space.setShip(ship);
        return getStateAt(x, y);
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

}
