package com.deeep.sod2.entities.enemyentities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.graphics.ShapeRenderer;

/*
 * Class : Turret
 * Package : com.deeep.sod2.entities
 * Author : Andreas
 * Date : 08-10-13
 */

public class Turret extends AnimatedEnemy {

    protected int level;
    protected float health;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Turret() {
    }

    public Turret(int id, int x, int y, float health, int level) {
        super(id, -1, x, y,"animations/turret_strip14",5);
        this.health = health;
        this.level = level;
        setTextureRegion(Assets.getAssets().getRegion("Tiles/turret"));
    }

}
