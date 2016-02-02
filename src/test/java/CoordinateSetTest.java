package test.java;

import main.java.CoordinateSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateSetTest {
    @Test
    public void testCorrectInput() {
        CoordinateSet set = new CoordinateSet("A0");
        assertEquals(0, set.getYValue());
        assertEquals(0, set.getXValue());

        set = new CoordinateSet("B1");
        assertEquals(1, set.getYValue());
        assertEquals(1, set.getXValue());

        set = new CoordinateSet("H9");
        assertEquals(7, set.getYValue());
        assertEquals(9, set.getXValue());
    }

    @Test
    public void testIgnoresExtraWhitespace() {
        CoordinateSet set = new CoordinateSet("   E   3    ");
        assertEquals(3, set.getXValue());
        assertEquals(4, set.getYValue());

    }
}
