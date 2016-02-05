package test.java;

import main.java.Fleet;
import main.java.Ship;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FleetTest {
    private Fleet fleet;
    private List<Ship> ships;

    @Before
    public void setup() {
        fleet = new Fleet();
        ships = fleet.getShips();
    }

    @Test
    public void testGetShipsLength() {
        assertEquals(5, ships.size());
    }

    public int countShipsOfSize(int size) {
        int shipCount = 0;

        for (Ship ship : ships) {
            if (ship.getSize() == size) {
                shipCount++;
            }
        }

        return shipCount;
    }

    @Test
    public void testGetShipsHasBattleship() {
        int shipCount = countShipsOfSize(4);
        assertEquals(1, shipCount);
    }

    @Test
    public void testGetShipsHasAircraftCarrier() {
        int shipCount = countShipsOfSize(5);
        assertEquals(1, shipCount);
    }

    @Test
    public void testGetShipsHasSubmarineAndDestroyer() {
        int shipCount = countShipsOfSize(3);
        assertEquals(2, shipCount);
    }

    @Test
    public void testGetShipsHasPatrolBoat() {
        int shipCount = countShipsOfSize(2);
        assertEquals(1, shipCount);
    }

}
