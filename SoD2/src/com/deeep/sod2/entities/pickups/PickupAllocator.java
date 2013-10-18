package com.deeep.sod2.entities.pickups;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/18/13
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class PickupAllocator extends Entity {

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public PickupAllocator() {
    }

    public PickupAllocator(int id, int owner, int x, int y) {
        super(id, owner, x, y);
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        //TODO look for the nearest pickups
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
