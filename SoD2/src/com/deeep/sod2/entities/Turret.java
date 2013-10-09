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

public class Turret extends Entity{

    protected int level;
    protected float health;

    public Turret(float x, float y, int level, float health) {
        this.x = x;
        this.y = y;
        this.level = level;
        this.health = health;
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        ShapeRenderer.setColor(new Color(1f-level/255f, 0f, 0f, 1f));
        ShapeRenderer.drawRectangle(spriteBatch, x, y, 1f, 1f, true);
        spriteBatch.draw(Assets.getAssets().getPlanetTexture(1), x, y, 1, 1);
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
}
