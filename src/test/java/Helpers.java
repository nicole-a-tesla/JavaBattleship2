package test.java;

import main.java.Ship;

import java.util.List;

public class Helpers {

    public static void sinkThisManyShips(List<Ship> ships, int numToSink) {
        for (int i = 0; i<numToSink; i++) {
            sinkThisShip(ships.get(i));
        }
    }

    public static void sinkThisShip(Ship ship) {
        int size = ship.getSize();

        for (int i=0; i<size; i++) {
            ship.logStrike();
        }
    }
}
