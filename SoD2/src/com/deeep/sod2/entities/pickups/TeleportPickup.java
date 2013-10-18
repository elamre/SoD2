package com.deeep.sod2.entities.pickups;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.entities.projectiles.TeleportProjectile;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/18/13
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class TeleportPickup extends Pickup {

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public TeleportPickup() {
    }

    public TeleportPickup(int id, int x, int y) {
        super(id, x, y, Assets.getAssets().getRegion("upgrades/teleport"));
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
        EntityManager.get().addEntitySinglePlayer(new TeleportProjectile(EntityManager.get().getNextSinglePlayerId(), -1, owner.getX(), owner.getY(), owner.getSnakeDirection(), owner));
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
