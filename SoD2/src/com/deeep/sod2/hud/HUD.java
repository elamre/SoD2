package com.deeep.sod2.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/18/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class HUD {
    private static HUD hud;

    public static HUD getHud() {
        if (hud == null) {
            hud = new HUD();
        }
        return hud;
    }

    public void draw(SpriteBatch spriteBatch) {
        //TODO draw last 3 pickups
    }
}
