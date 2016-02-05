package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    public int size;
    private ArrayList grid;
    private List<Ship> ships;

    public Board(Fleet fleet, int size) {
        this.size = size;
        this.grid = buildGrid();
        this.ships = fleet.getShips();
    }

    public void setAllShipsAtRandom() {
        for (Ship ship: ships) {
            setShipAtRandom(ship);
        }
    }

    private void setShipAtRandom(Ship ship) {
        int randX = new Random().nextInt(9);
        int randY = new Random().nextInt(9);

        if (getStateAt(randX, randY) == State.WATER) {
            setShipAt(ship, randX, randY);
        } else {
            setShipAtRandom(ship);
        }
    }

    public List<Ship> getShips() {
        return ships;
    }

    public boolean allSunk() {
        boolean allSunk = true;

        for (Ship ship: ships) {
            if (!ship.isSunk()) {
                allSunk = false;
            }
        }
        return allSunk;
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
