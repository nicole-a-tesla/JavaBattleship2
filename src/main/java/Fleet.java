package main.java;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private List<Ship> ships = new ArrayList<>();

    public Fleet() {
        ships = buildShips();
    }

    private List<Ship> buildShips() {

        ships.add(new Ship("Aircraft Carrier", 5));
        ships.add(new Ship("Battleship", 4));
        ships.add(new Ship("Submarine", 3));
        ships.add(new Ship("Destroyer", 3));
        ships.add(new Ship("Patrol Boat", 2));

        return ships;
    }

    public List<Ship> getShips() {
        return ships;

    }


}
