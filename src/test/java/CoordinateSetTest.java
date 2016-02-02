package test.java;

import main.java.CoordinateSet;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    public void testIgnoresWhitespace() {
        CoordinateSet set = new CoordinateSet("   E   3    ");
        assertEquals(3, set.getXValue());
        assertEquals(4, set.getYValue());
    }

    @Test
    public void ignoresCase() {
        CoordinateSet set = new CoordinateSet("a0");
        assertEquals(0, set.getYValue());
        assertEquals(0, set.getXValue());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testThrowsErrorOnInvalidInput() {
        thrown.expect(IllegalArgumentException.class);
        new CoordinateSet("0,0");
    }

    @Test
    public void throwsErrorOnNumericalStart() {
        thrown.expect(IllegalArgumentException.class);
        new CoordinateSet("10");
    }

    @Test
    public void throwsErrorOnShortNonsense() {
        thrown.expect(IllegalArgumentException.class);
        new CoordinateSet(" 1");
    }

    @Test
    public void throwsErrorOnWhitespaceOnly() {
        thrown.expect(IllegalArgumentException.class);
        new CoordinateSet(" ");
    }

    @Test
    public void throwsErrorOnOutOfBoundsLetter() {
        thrown.expect(IllegalArgumentException.class);
        new CoordinateSet("v1");
    }
}
