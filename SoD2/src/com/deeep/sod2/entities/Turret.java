package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.graphics.ShapeRenderer;

/*
 * Class : Turret
 * Package : com.deeep.sod2.entities
 * Author : Andreas
 * Date : 08-10-13
 */

public class Turret extends Entity {

    protected int level;
    protected float health;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Turret() {
    }

    public Turret(int id, int x, int y, float health, int level) {
        super(id, -1, x, y);
        this.health = health;
        this.level = level;
        setTextureRegion(Assets.getAssets().getRegion("Tiles/turret"));
    }


    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
    }

    @Override
    public void implementUpdate_1(float deltaT) {
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
