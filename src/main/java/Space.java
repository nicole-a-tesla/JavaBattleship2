package main.java;

public class Space {
    private State state;
    private Ship ship;

    public Space() {
        this.state = State.WATER;
    }

    public State getState() {
        updateStateIfSunk();
        return state;
    }

    private void updateStateIfSunk() {
        if (shipIsSunk()) {
            state = State.SUNK;
        }
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
        state = State.HIT;
        updateStateIfSunk();
    }


    public boolean shipIsSunk() {
        return ship != null && ship.isSunk();
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        state = State.SHIP;
    }

}
