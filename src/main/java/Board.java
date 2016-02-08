package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        ships.forEach(this::setShipAtRandom);
    }

    private void setShipAtRandom(Ship ship) {
        String[] orientations = {"vertical", "horizontal"};
        String orientation = orientations[new Random().nextInt(2)];
        int randX = new Random().nextInt(10);
        int randY = new Random().nextInt(10);

        if (!attemptSet(ship, randX, randY, orientation, 0)) {
            setShipAtRandom(ship);
        }
    }

    public boolean attemptSet(Ship ship, int x, int y, String orientation, int depth) {
        if (ship.getSize() == depth) {
            return true;
        }

        if (positionIsOnBoard(x, y) && spaceIsEmpty(x, y)) {
            if (Objects.equals(orientation, "horizontal")) {

                if (attemptSet(ship, x+1, y, orientation, depth+1)) {
                    setShipAt(ship, x, y);
                    return true;
                }

            } else if (Objects.equals(orientation, "vertical")) {

                if (attemptSet(ship, x, y+1, orientation, depth+1)) {
                    setShipAt(ship, x, y);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean attemptHorizontalSet(Ship ship, int x, int y, int depth) {
        if (ship.getSize() == depth) {
            return true;
        }

        if (positionIsOnBoard(x, y) && spaceIsEmpty(x, y)) {
                if (attemptHorizontalSet(ship, x + 1, y, depth+1)) {
                    setShipAt(ship, x, y);
                    return true;
                }
        }

        return false;
    }

    public boolean attemptVerticalSet(Ship ship, int x, int y, int depth) {
        if (ship.getSize() == depth) {
            return true;
        }

        if (positionIsOnBoard(x, y) && spaceIsEmpty(x, y)) {
            if (attemptVerticalSet(ship, x, y+1, depth+1)) {
                setShipAt(ship, x, y);
                return true;
            }
        }

        return false;
    }

    private boolean positionIsOnBoard(int x, int y) {
        return x < 10 && y < 10;
    }

    private boolean spaceIsEmpty(int x, int y) {
        return getStateAt(x, y) == State.WATER;
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
