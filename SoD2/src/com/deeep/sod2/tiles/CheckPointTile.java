package com.deeep.sod2.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.graphics.ShapeRenderer;
import com.deeep.sod2.utility.Logger;

/**
 * Name: CheckPointTile
 * Pack: com.deeep.sod2.tiles
 * User: andreaskruhlmann
 * Date: 10/9/13
 */

public class CheckPointTile extends AbstractTile {

    public CheckPointTile(int x, int y) {
        setX(x);
        setY(y);
        setColor(new Color(0.05f, 0.7f, 0.1f, 0.5f));
        this.textureRegion = Assets.getAssets().getRegion("Tiles/glass");
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, x, y, 1, 1, 1, 1, 1, 1, 0);
        ShapeRenderer.setColor(color);
        ShapeRenderer.drawRectangle(spriteBatch, x, y, 1, 1, true);
    }

    @Override
    public void onStep(Entity stepper) {
        ((Snake) stepper).setCheckpoint(x, y, ((Snake) stepper).getDir());
        Logger.getInstance().debug(this.getClass(), "Snake got a new checkpoint");
    }
}
