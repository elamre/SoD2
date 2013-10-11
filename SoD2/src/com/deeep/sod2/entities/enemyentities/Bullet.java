package com.deeep.sod2.entities.enemyentities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.graphics.PVector;

/**
 * Created with IntelliJ IDEA.
 * User: Andreas
 * Date: 11-10-13
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */

/*
 * Class : Bullet
 * Package : com.deeep.sod2.entities.enemyentities
 * Author : Andreas
 * Date : 11-10-13
 */

public class Bullet extends Entity{

    /** Angle */
    private float theta;

    private PVector velocity;
    private PVector location;

    public Bullet(float theta, PVector location, PVector velocity) {
        this.theta = theta;
        this.location = location;
        this.velocity = velocity;
    }

    /**
     * Use this function instead of the constructor
     */
    @Override
    public void onCreate() {

    }

    @Override
    public void implementUpdate_1(float deltaT) {

    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {

    }

}
