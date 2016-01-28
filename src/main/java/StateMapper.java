package main.java;

import java.util.HashMap;

public class StateMapper {
    public String mapState(State state) {
        return dictionary.get(state);
    }

    public static final HashMap<State, String> dictionary = new HashMap<State, String>() {
        {
            put(State.WATER, " \uD83C\uDF0A ");
            put(State.SHIP, " \uD83C\uDF0A️ "); // same as water for now
            put(State.MISS, " \uD83D\uDCA8 ");
            put(State.HIT, " \uD83D\uDCA5 ");
            put(State.SUNK, " ♨️ ");
        }
    };
}
