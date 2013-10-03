package com.deeep.sod2.graphics;

/*
 * Class : Planet
 * Package : com.deeep.sod2.graphics
 * Author : Andreas
 * Date : 01-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet extends Particle {

    public int planetId;

    public Planet(PVector v, float w, float h, int planetId) {
        super(v, null, null, w, h);
        this.planetId = planetId;
    }

    /**
     * Updates the planet
     *
     * @param delta delta to update the planet with
     */
    @Override
    public void update(float delta) {
        //location.add(velocity);
    }

    /**
     * Updates the planet
     *
     * @param graphics graphics to draw the planet
     */
    @Override
    public void draw(SpriteBatch graphics) {
        graphics.draw(Assets.getAssets().getPlanetTexture(planetId), location.x, location.y, getWidth(), getHeight());
    }

}
