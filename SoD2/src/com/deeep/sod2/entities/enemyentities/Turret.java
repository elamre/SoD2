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
    protected  float maxHealth;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Turret() {
    }

    public Turret(int id, int x, int y, float health, int level) {
        super(id, -1, x, y,"animations/turret_strip14",5);
        this.health = health;
        this.maxHealth = health;
        this.level = level;
        setTextureRegion(Assets.getAssets().getRegion("Tiles/turret"));
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        ShapeRenderer.setColor(new Color(0xff0000ff));
        ShapeRenderer.drawRectangle(spriteBatch, x+0.1f, y+0.95f, (0.8f)-((maxHealth-health)/maxHealth)*0.8f, -0.2f, true);
    }
}
