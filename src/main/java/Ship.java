package main.java;

public class Ship {
    private int size;
    private int hitCount;

    public Ship(int size) {
        this.size = size;
        this.hitCount = 0;
    }

    public void logStrike() {
        hitCount++;
    }

    public boolean isSunk() {
        return hitCount >= size;
    }
}
