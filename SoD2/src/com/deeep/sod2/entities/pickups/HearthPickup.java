package com.deeep.sod2.entities.pickups;

import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/5/13
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HearthPickup extends Pickup {
    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public HearthPickup() {
    }

    public HearthPickup(int id, int x, int y) {
        super(id, x, y, Assets.getAssets().getRegion("upgrades/life"));
    }

    @Override
    public void action() {

    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {

    }
}
