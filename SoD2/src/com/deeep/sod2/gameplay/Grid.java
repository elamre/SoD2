package com.deeep.sod2.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.graphics.ShapeRenderer;
import com.deeep.sod2.io.Save;
import com.deeep.sod2.tiles.AbstractTile;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/30/13
 * Time: 7:42 PM
 */
public class Grid {
    private float size = 0;
    /** Width and height of grid in units (tiles) */
    private int width = 0;
    private int height = 0;
    /** Color of grid lines */
    private Color color;
    /** Tile array */
    private AbstractTile[] tiles;

    public Grid(float size, int width, int height, Color color, Save save) {
        this.size = size;
        this.width = width;
        this.height = height;
        this.color = color;
        this.tiles = save.getTiles();
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void draw(SpriteBatch spriteBatch) {
/*        ShapeRenderer.setColor(color);
        for (int x = 0; x < width + 1; x++) {
            for (int y = 0; y < height + 1; y++) {
                ShapeRenderer.drawLine(spriteBatch, x, 0, x, y, 0.025f);
                ShapeRenderer.drawLine(spriteBatch, 0, y, x, y, 0.025f);
            }
        }*/
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    tiles[x + y * width].draw(spriteBatch);
                } catch (NullPointerException e) {
                }
            }
        }
    }
}
