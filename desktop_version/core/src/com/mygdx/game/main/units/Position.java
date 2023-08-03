package com.mygdx.game.main.units;

public class Position {
    public int x;
    public int y;

    private static int leftSideX = 1;
    private static int leftSideY = 1;
    private static int rightSideX = 10;
    private static int rightSideY = 10;

    protected Position(boolean leftSide) {
        if (leftSide) {
            x = leftSideX;
            y = leftSideY++;
            if (leftSideY == 11) {
                leftSideY = 1;
                leftSideX++;
            }
        }
        else {
            x = rightSideX;
            y = rightSideY--;
            if (rightSideY == 0) {
                rightSideY = 10;
                rightSideX--;
            }
        }
    }

    protected Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected double getDistance(Position target) {
        return Math.sqrt(
            Math.pow((this.x - target.x), 2) +
            Math.pow((this.y - target.y), 2));
    }

    @Override
    public boolean equals(Object position) {
        Position pos = (Position) position;
        return this.x == pos.x && this.y == pos.y;
    }
}
