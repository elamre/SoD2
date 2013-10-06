package com.deeep.sod2.tiles;

/*
 * Class : ObstacleTile
 * Package : com.deeep.sod2.tiles
 * Author : Andreas
 * Date : 05-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.graphics.ShapeRenderer;

public class ObstacleTile extends AbstractTile{

    public ObstacleTile(int x, int y) {
        setColor(new Color(0.4f, 0.2f, 0.1f, 0.3f));
        setX(x);
        setY(y);
    }

    public void draw(SpriteBatch spriteBatch){
        ShapeRenderer.setColor(color);
        ShapeRenderer.drawRectangle(spriteBatch, x, y, 1, 1, true);
    }

}
