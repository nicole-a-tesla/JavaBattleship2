package com.nicolemccabe.javabattleship2;

public class Space {
    private State state;
    private boolean hasShip;

    public Space() {
        this.state = State.WATER;
        this.hasShip = false;
    }

    public State getState() {
        return state;
    }

    public boolean hasShip() {
        return hasShip;
    }

    public void logStrike() {
        state = State.MISS;
    }

}
