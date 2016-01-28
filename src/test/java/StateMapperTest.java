package test.java;

import com.nicolemccabe.javabattleship2.State;
import com.nicolemccabe.javabattleship2.StateMapper;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StateMapperTest {
    @Test
    public void mapsWater() {
        String expectedWater = " \uD83C\uDF0A ";
        StateMapper mapper = new StateMapper();
        String water = mapper.mapState(State.WATER);
        assertEquals(expectedWater, water);
    }

    @Test
    public void mapsMiss() {
        String expectedMiss = " \uD83D\uDCA8 ";
        StateMapper mapper = new StateMapper();
        String miss = mapper.mapState(State.MISS);
        assertEquals(expectedMiss, miss);
    }

    @Test
    public void mapsHit() {
        String expectedHit = " \uD83D\uDCA5 ";
        StateMapper mapper = new StateMapper();
        String hit = mapper.mapState(State.HIT);
        assertEquals(expectedHit, hit);
    }

    @Test
    public void mapsSunk() {
        String expectedSunk = " ♨️ ";
        StateMapper mapper = new StateMapper();
        String sunk = mapper.mapState(State.SUNK);
        assertEquals(expectedSunk, sunk);
    }

    @Ignore // no ship printing just yet!
    public void mapsShip() {
        String expectedShip = " ⛵️️ ";
        StateMapper mapper = new StateMapper();
        String ship = mapper.mapState(State.SHIP);
        assertEquals(expectedShip, ship);
    }
}

