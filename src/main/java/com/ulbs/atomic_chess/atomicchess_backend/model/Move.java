package com.ulbs.atomic_chess.atomicchess_backend.model;

public class Move {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    // Getters and Setters
    public int getFromX() { return fromX; }
    public void setFromX(int fromX) { this.fromX = fromX; }

    public int getFromY() { return fromY; }
    public void setFromY(int fromY) { this.fromY = fromY; }

    public int getToX() { return toX; }
    public void setToX(int toX) { this.toX = toX; }

    public int getToY() { return toY; }
    public void setToY(int toY) { this.toY = toY; }
}