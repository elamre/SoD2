package com.deeep.sod2.tiles;

/*
 * Class : RegularTile
 * Package : com.deeep.sod2.tiles
 * Author : Andreas
 * Date : 05-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.graphics.ShapeRenderer;

public class RegularTile extends AbstractTile{

    public RegularTile(int x, int y) {
        setColor(new Color(0.1f, 0.4f, 0.5f, 0.3f));
        setX(x);
        setY(y);
    }

    public RegularTile(int x, int y, Color c) {
        setColor(c);
        setX(x);
        setY(y);
    }

    @Override
    public void draw(SpriteBatch spriteBatch){
        ShapeRenderer.setColor(color);
        ShapeRenderer.drawRectangle(spriteBatch, x, y, 1, 1, true);
    }

    @Override
    public void onStep(Entity stepper) {
    }

}
