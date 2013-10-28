package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.projectiles.TurretBullet;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/3/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Head extends Entity implements CollideAble {
    private Direction direction = Direction.NORTH;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Head() {
    }

    public Head(int id, int owner, int x, int y) {
        super(id, owner, x, y);
    }

    public void setSkin(int skinId) {
        setTextureRegion(Assets.getAssets().getRegion("snakes/snake_" + skinId + "_head"));
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
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

        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        setAngle(direction.getValue());
    }

    public Direction getDirection() {
        return direction;
    }
}
