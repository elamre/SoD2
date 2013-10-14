package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.pickups.Pickup;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/3/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tail extends Entity implements CollideAble {
    private Pickup pickup;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Tail() {
    }

    public Tail(int id, int owner, int x, int y) {
        super(id, owner, x, y);
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
        pickup.setWidth(0.7f);
        pickup.setHeight(0.7f);
    }

    public void setSkin(int skin) {
        setTextureRegion(Assets.getAssets().getRegion("snakes/snake_1_tail"));
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        setSkin(1);
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        if (pickup != null) {
            pickup.setX(getX());
            pickup.setY(getY());
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setAngle(float angle) {
        this.angle = angle;
        pickup.setAngle(angle);
    }

    public boolean action(Snake snake) {
        if (pickup != null) {
            return pickup.action(snake);
        }
        return false;
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        if (pickup != null) {
            pickup.draw(spriteBatch);
            // spriteBatch.draw(pickup.getTextureRegion(), x, y);
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void Collide(Entity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
