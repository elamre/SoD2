package com.deeep.sod2.entities.enemyentities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.*;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/20/13
 * Time: 3:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Laser extends AnimatedEntity implements CollideAble {
    private Direction direction;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Laser() {
    }

    public Laser(int id, int owner, int x, int y, Direction direction) {
        super(id, owner, x, y, "animations/laser_strip9", 0.02f, false);
        this.direction = direction;

    }

    @Override
    public void onCreate_1() {
        setZ(1);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementUpdate_2(float deltaT) {
        //TODO do this only once
        setAngle(direction.getValue() + 90);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void Collide(Entity entity) {
        if(entity instanceof Snake){
            die();
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
