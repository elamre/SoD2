package com.deeep.sod2.tiles;

/*
 * Class : EmptyTile
 * Package : com.deeep.sod2.tiles
 * Author : Andreas
 * Date : 05-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.graphics.ShapeRenderer;

public class EmptyTile extends AbstractTile{

    public EmptyTile(int x, int y) {
        setColor(new Color(0.4f, 0.2f, 0.1f, 0.3f));
        setX(x);
        setY(y);
    }

    @Override
    public void draw(SpriteBatch spriteBatch){
    }


    @Override
    public void onStep(Entity stepper) {
        //TODO Fall through the layer
    }

}
