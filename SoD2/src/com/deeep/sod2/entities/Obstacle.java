package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.projectiles.Projectile;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/18/13
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Obstacle extends Entity implements CollideAble {


    public Obstacle(int id, int owner, int x, int y) {
        super(id, owner, x, y);
    }

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Obstacle() {
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        setTextureRegion(this.textureRegion = Assets.getAssets().getRegion("Tiles/obstacle"));
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void Collide(Entity entity) {
        if (entity instanceof Projectile) {
            die();
            entity.die();
        }
    }
}
