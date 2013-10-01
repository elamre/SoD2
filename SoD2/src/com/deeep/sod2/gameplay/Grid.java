package com.deeep.sod2.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.graphics.ShapeRenderer;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/30/13
 * Time: 7:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Grid {
    private float size = 0;
    private int width = 0;
    private int height = 0;
    private Color color;

    public Grid(float size, int width, int height, Color color) {
        this.size = size;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(SpriteBatch spriteBatch) {
        ShapeRenderer.setColor(color);
        for (int x = 0; x < width + 1; x++) {
            for (int y = 0; y < height + 1; y++) {
                ShapeRenderer.drawLine(spriteBatch, x, 0, x, y, 0.025f);
                ShapeRenderer.drawLine(spriteBatch, 0, y, x, y, 0.025f);
            }
        }
    }
}
