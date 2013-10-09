package com.deeep.sod2.tiles;

/*
 * Class : RegularTile
 * Package : com.deeep.sod2.tiles
 * Author : Andreas
 * Date : 05-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.graphics.ShapeRenderer;

public class RegularTile extends AbstractTile {
    TextureRegion textureRegion;

    public RegularTile(int x, int y) {
        setColor(new Color(0.1f, 0.4f, 0.5f, 0.3f));
        setX(x);
        setY(y);
        textureRegion = Assets.getAssets().getRegion("Tiles/glass");
    }

    public void draw(SpriteBatch spriteBatch) {
        //ShapeRenderer.setColor(color);
        //ShapeRenderer.drawRectangle(spriteBatch, x, y, 1, 1, true);
        spriteBatch.draw(textureRegion, x, y, 1, 1, 1, 1, 1, 1, 0);

    }

}
