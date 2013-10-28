package com.deeep.sod2.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/24/13
 * Time: 1:03 AM
 * To change this template use File | Settings | File Templates.
 */
public enum Direction {
    NORTH(90, 0, 1), EAST(0, 1, 0), SOUTH(270, 0, -1), WEST(180, -1, 0);
    private int dir;
    private float radians;
    private Vector2 vector2;

    Direction(int dir, int x, int y) {
        this.dir = dir;
        this.radians = (float) Math.toRadians(dir);
        vector2 = new Vector2(x, y);
    }

    public com.deeep.sod2.entities.Direction getOpposite() {
        if (this == NORTH)
            return SOUTH;
        if (this == EAST)
            return WEST;
        if (this == SOUTH)
            return NORTH;
        if (this == WEST)
            return EAST;
        return NORTH;
    }

    public Direction setDirection(int angle) {
        switch (angle) {
            case 1:
            case 90:
                return Direction.NORTH;
            case 2:
            case 0:
                return Direction.EAST;
            case 3:
            case 270:
                return Direction.SOUTH;
            case 4:
            case 180:
                return Direction.WEST;
        }
        return NORTH;
    }

    public int getValue() {
        return dir;
    }

    public float getRadians() {
        return radians;
    }

    public Vector2 getVector() {
        return vector2;
    }

    public boolean clockWise(Direction curDir) {
        int checkValue = this.getValue() - 90;
        if (checkValue < 0)
            checkValue = 270;
        System.out.println("Checkvalue: " + checkValue + " curdir: " + curDir.getValue());
        if (checkValue == curDir.getValue())
            return true;
        return false;  //TODO clockWise
    }
}
