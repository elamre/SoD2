package com.deeep.sod2.tiles;

/*
 * Class : ObstacleTile
 * Package : com.deeep.sod2.tiles
 * Author : Andreas
 * Date : 05-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.graphics.ShapeRenderer;
import com.deeep.sod2.utility.Logger;

public class ObstacleTile extends AbstractTile {

    public ObstacleTile(int x, int y) {
        setX(x);
        setY(y);
        this.textureRegion = Assets.getAssets().getRegion("Tiles/obstacle");
    }

    @Override
    public void implementDraw(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, x, y, 1, 1, 1, 1, 1, 1, 0);
    }

    @Override
    public void onStep(Entity stepper) {
        Logger.getInstance().debug(this.getClass(), "somebody stepped on me at: " + x + ", " + y);
        stepper.die();
    }

}
