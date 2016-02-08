package test.java;

import main.java.Ship;
import main.java.ShipSetArgs;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ShipSetArgsTest {
    @Test
    public void testHorizontalTransform() {
        Ship ship = new Ship("Dr. Shippington", 2);
        ShipSetArgs args = new ShipSetArgs(ship, 0, 0, "horizontal");
        ShipSetArgs transformed = args.nextArgs();
        assertEquals(1, transformed.getX());
        assertEquals(0, transformed.getY());
    }

    @Test
    public void testVerticalTransform() {
        Ship ship = new Ship("Dr. Shippington the 2nd", 2);
        ShipSetArgs args = new ShipSetArgs(ship, 0, 0, "vertical");
        ShipSetArgs transformed = args.nextArgs();
        assertEquals(0, transformed.getX());
        assertEquals(1, transformed.getY());
    }
}
