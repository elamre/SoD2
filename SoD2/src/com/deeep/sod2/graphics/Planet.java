package com.deeep.sod2.graphics;

/*
 * Class : Planet
 * Package : com.deeep.sod2.graphics
 * Author : Andreas
 * Date : 01-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.utility.Camera;

public class Planet extends Particle {

    /** to keep track of how much the camera has moved since it last updated the stars position */
    private float oldCamX, oldCamY;

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
        super.update(delta);
        location.x += (Camera.getInstance().getX() - oldCamX) * .2f;
        location.y += (Camera.getInstance().getY() - oldCamY) * .2f;

        oldCamX = Camera.getInstance().getX();
        oldCamY = Camera.getInstance().getY();
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
