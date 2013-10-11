package com.deeep.sod2.entities.projectiles;

import com.deeep.sod2.entities.CollideAble;
import com.deeep.sod2.entities.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/10/13
 * Time: 6:55 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Projectile extends Entity implements CollideAble {
    private float dy, dx;
    private float speed = 0;
    private float fuel = 0;
    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    protected Projectile() {
    }

    protected Projectile(int id, int owner, float x, float y, float speed, float fuel, float angle) {
        super(id, owner, x, y);
        this.speed = speed;
        this.fuel = fuel;
        dy = (float) Math.sin(angle);
        dx = (float) Math.cos(angle);
        setAngle((float) Math.toDegrees(angle));
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        x += dx * deltaT * speed;
        y += dy * deltaT * speed;
        fuel -= deltaT;
        if (fuel <= 0)
            die();
        implementUpdate_2(deltaT);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public abstract void implementUpdate_2(float deltaT);

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }
}
