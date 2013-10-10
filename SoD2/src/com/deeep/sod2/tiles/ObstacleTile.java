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

public class ObstacleTile extends AbstractTile {
    TextureRegion textureRegion;

    public ObstacleTile(int x, int y) {
        setColor(new Color(0.6f, 0.3f, 0.2f, 0.5f));
        setX(x);
        setY(y);
        this.textureRegion = Assets.getAssets().getRegion("Tiles/obstacle");
    }

    public void draw(SpriteBatch spriteBatch){
        ShapeRenderer.setColor(color);
        ShapeRenderer.drawRectangle(spriteBatch, x, y, 1, 1, true);
        //spriteBatch.draw(textureRegion, x, y);
        spriteBatch.draw(textureRegion, x, y, 1, 1, 1, 1, 1, 1, 0);

    }

    @Override
    public void onStep(Entity stepper) {
    }

}
