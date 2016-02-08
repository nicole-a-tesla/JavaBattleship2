package main.java;

public class Ship {
    private int size;
    private int hitCount;
    private String name;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.hitCount = 0;
    }

    public void logStrike() {
        hitCount++;
    }

    public boolean isSunk() {
        return hitCount >= size;
    }

    public int getSize() {
        return size;
    }
}
