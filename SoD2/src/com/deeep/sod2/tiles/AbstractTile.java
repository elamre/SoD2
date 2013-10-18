package com.deeep.sod2.tiles;

/*
 * Class : Tile
 * Package : com.deeep.sod2.tiles
 * Author : Andreas
 * Date : 05-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.utility.Camera;

public abstract class AbstractTile {

    protected TileType tileType = TileType.EMPTY;
    /** X and y position in units */
    protected int x, y;
    /** Tile color */
    protected Color color;
    /** Texture for tile */
    TextureRegion textureRegion;

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

    public void draw(SpriteBatch spriteBatch) {
        if (Camera.getInstance().inVision(x, y, 1, 1)) {
            implementDraw(spriteBatch);
        }
    }

    public abstract void implementDraw(SpriteBatch spriteBatch);

    public abstract void onStep(Entity stepper);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
