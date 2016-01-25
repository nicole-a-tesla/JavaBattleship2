package com.nicolemccabe.javabattleship2;

import java.util.HashMap;

public class StateMapper {
    public String mapState(State state) {
        return dictionary.get(state);
    }

    public static final HashMap<State, String> dictionary = new HashMap<State, String>() {
        {
            put(State.WATER, " \uD83C\uDF0A ");
            put(State.SHIP, " ^ ");
            put(State.MISS, " \uD83D\uDCA8 ");
            put(State.HIT, " \uD83D\uDCA5 ");
        }
    };
}
