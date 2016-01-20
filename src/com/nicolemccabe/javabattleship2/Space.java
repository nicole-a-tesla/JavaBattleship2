package com.nicolemccabe.javabattleship2;

public class Space {
    private State state;

    public Space() {
        this.state = State.WATER;
    }

    public State getState() {
        return state;
    }

}
