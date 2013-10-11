package com.deeep.sod2.entities.projectiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/10/13
 * Time: 8:24 PM
 */
public class SnakeBullet extends Projectile{

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public SnakeBullet() {
    }

    public SnakeBullet(int id, int owner, float x, float y, float speed, float fuel, float angle) {
        super(id, owner, x, y, speed, fuel, angle);
    }

    @Override
    public void implementUpdate_2(float deltaT) {
    }

    @Override
    public void Collide(Entity entity) {
        //TODO takeDamage() or something
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        setTextureRegion(Assets.getAssets().getRegion("projectiles/bullet1"));
        setWidth(0.5f);
        setHeight(0.5f);
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
    }
}
