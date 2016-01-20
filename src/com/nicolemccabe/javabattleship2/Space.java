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
        if (state == State.WATER) {
            state = State.MISS;
        } else {
            state = State.HIT;
        }
    }

    public void setShip() {
        state = State.SHIP;
    }

}
