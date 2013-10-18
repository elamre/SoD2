package com.deeep.sod2.entities.pickups;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/18/13
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeyPickup extends Pickup {

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public KeyPickup() {
    }

    public KeyPickup(int id, int x, int y) {
        super(id, x, y, Assets.getAssets().getRegion("upgrades/key"));
    }

    /**
     * the action to be executed when the pickup is used. If nothing should happen, and the pick up should not removed,
     * return false;
     *
     * @param owner use this for origin and such
     * @return true if the pickup has been used
     */
    @Override
    public boolean action(Snake owner) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
