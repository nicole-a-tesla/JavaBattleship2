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
        if (state == State.WATER || state == State.MISS) {
            state = State.MISS;
        } else {
            ship.logStrike();
            updateStateAccordingToShipState();
        }
        return state;
    }

    private void updateStateAccordingToShipState() {
        if (shipIsSunk()) {
            state = State.SUNK;
        } else {
            state = State.HIT;
        }
    }


    public boolean shipIsSunk() {
        return ship != null && ship.isSunk();
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        state = State.SHIP;
    }

}
