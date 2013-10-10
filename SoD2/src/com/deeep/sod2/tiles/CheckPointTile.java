package com.deeep.sod2.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.graphics.ShapeRenderer;

/**
 * Name: CheckPointTile
 * Pack: com.deeep.sod2.tiles
 * User: andreaskruhlmann
 * Date: 10/9/13
 */

public class CheckPointTile extends AbstractTile{

    public CheckPointTile(int x, int y){
        setX(x);
        setY(y);
        setColor(new Color(0.05f, 0.7f, 0.1f, 0.5f));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        ShapeRenderer.setColor(color);
        ShapeRenderer.drawRectangle(spriteBatch, x, y, 1, 1, true);
    }

    @Override
    public void onStep(Entity stepper){
        ((Snake) stepper).checkPointX = x;
        ((Snake) stepper).checkPointY = y;
    }
}
