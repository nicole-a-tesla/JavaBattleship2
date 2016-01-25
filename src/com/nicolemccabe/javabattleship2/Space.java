package com.nicolemccabe.javabattleship2;

public class Space {
    private State state;
    private Ship ship;

    public Space() {
        this.state = State.WATER;
    }

    public State getState() {
        return state;
    }

    public State logStrike() {
        if (state == State.WATER) {
            state = State.MISS;
        } else {
            ship.logStrike();
            state = State.HIT;
        }
        return state;
    }


    public boolean isShipSunk() {
        return ship != null && ship.isSunk();
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        state = State.SHIP;
    }

}
