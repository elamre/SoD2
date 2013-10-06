package com.deeep.sod2.tiles;

/*
 * Class : Tile
 * Package : com.deeep.sod2.tiles
 * Author : Andreas
 * Date : 05-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.graphics.ShapeRenderer;

public abstract class Tile {

    private TileType tileType = TileType.EMPTY;

    /** X and y position in units*/
    private int x, y;

    /** Tile color */
    private Color color;

    public Tile(TileType tileType, int x, int y) {
        setTileType(tileType);
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public abstract void draw(SpriteBatch spriteBatch);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
