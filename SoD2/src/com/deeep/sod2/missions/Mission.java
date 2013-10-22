package com.deeep.sod2.missions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.deeep.sod2.Core;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.io.Save;
import com.deeep.sod2.screens.GameScreen;
import com.deeep.sod2.utility.Camera;

import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/12/13
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mission {
    private TextureRegion textureRegion;
    private String entityMap;
    private Rectangle hitBox;
    private String map;
    private float x, y;

    public Mission(String entityMap, String map) {
        textureRegion = Assets.getAssets().getRegion("gui/missionbutton");
        this.entityMap = map;
        this.map = entityMap;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, x, y, 1, 1);
    }

    public String toString() {
        return "Mission: " + entityMap + " map: " + map;
    }

    public void checkPressed() {
        if (hitBox.contains(Camera.getInstance().getTouchUnitX(), Camera.getInstance().getTouchUnitY())) {
            System.out.println("loading: " + this);
            Core.getCore().setScreen(new GameScreen(new Save(map, entityMap)));
        }
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x, y, 1, 1);
    }
}
