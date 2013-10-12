package com.deeep.sod2.entities;

/*
 * Class : GUIPlanet
 * Package : com.deeep.sod2.entities
 * Author : Andreas
 * Date : 12-10-13
 */

public class GUIPlanet {
    public float getzIndex() {
        return zIndex;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getIdentifier() {
        return identifier;
    }

    private float zIndex;
    private float x;
    private float y;
    private float width;
    private float height;
    private String identifier;

    @Override
    public String toString() {
        return "GUIPlanet{" +
                "zIndex=" + zIndex +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", identifier='" + identifier + '\'' +
                '}';
    }

    public GUIPlanet(String identifier, float zIndex, float x, float y, float width, float height) {
        this.identifier = identifier;
        this.zIndex = zIndex;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

}
