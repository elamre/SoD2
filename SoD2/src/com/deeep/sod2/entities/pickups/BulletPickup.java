package com.deeep.sod2.entities.pickups;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.entities.projectiles.SnakeBullet;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/6/13
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class BulletPickup extends Pickup {
    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public BulletPickup() {
    }

    public BulletPickup(int id, int x, int y) {
        super(id, x, y, Assets.getAssets().getRegion("upgrades/Bullet2"));
    }

    @Override
    public boolean action(EntityManager entityManager, Snake owner) {
        entityManager.addEntitySinglePlayer(new SnakeBullet(entityManager.getNextSinglePlayerId(), -1, owner.getX(), owner.getY(), 5 / owner.getTickTime(), 10, owner.getSnakeDirection().getRadians()));
        return true;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
