package main.java;

public class ShipSetArgs {
    private Ship ship;
    private int x;
    private int y;
    private String orientation;

    public ShipSetArgs(Ship ship, int x, int y, String orientation) {
        this.ship = ship;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ship getShip() {
        return ship;
    }

    public ShipSetArgs nextArgs() {
        if (orientation == "horizontal") {
            return new ShipSetArgs(ship, x + 1, y, orientation);
        } else {
            return new ShipSetArgs(ship, x, y+1, orientation );
        }
    }
}
