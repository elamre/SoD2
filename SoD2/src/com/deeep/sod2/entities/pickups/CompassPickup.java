package com.deeep.sod2.entities.pickups;

import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/6/13
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompassPickup extends Pickup {
    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public CompassPickup() {
    }

    public CompassPickup(int id, int x, int y) {
        super(id, x, y, Assets.getAssets().getRegion("upgrades/compass"));
    }

    @Override
    public boolean action(Snake owner) {
        return true;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}