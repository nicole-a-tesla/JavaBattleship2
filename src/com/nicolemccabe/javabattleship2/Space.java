package com.nicolemccabe.javabattleship2;

public class Space {
    private State state;

    public Space() {
        this.state = State.WATER;
    }

    public State getState() {
        return state;
    }

    public void logStrike() {
        state = State.MISS;
    }

    public void setShip() {
        state = State.SHIP;
    }

}
