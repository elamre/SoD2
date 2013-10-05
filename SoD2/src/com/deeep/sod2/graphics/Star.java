package com.deeep.sod2.graphics;

/*
 * Class : Star
 * Package : com.deeep.sod2.graphics
 * Author : Andreas
 * Date : 03-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.deeep.sod2.entities.Block;
import com.deeep.sod2.particle.Sequencer;
import com.deeep.sod2.utility.Camera;

public class Star extends Particle {

    /** to keep track of how much the camera has moved since it last updated the stars position */
    private float oldCamX, oldCamY;
    private float maxBrightness;

    public Star(PVector v, Color c, Sequencer sequencer, float maxBrightness, float w, float h) {
        super(v, c, sequencer, w, h);
        this.maxBrightness = maxBrightness;
    }

    public void update(float deltaT) {
        super.update(deltaT);
        location.x += (Camera.getInstance().getX() - oldCamX) * (0.95f * maxBrightness);
        location.y += (Camera.getInstance().getY() - oldCamY) * (0.95f * maxBrightness);

        oldCamX = Camera.getInstance().getX();
        oldCamY = Camera.getInstance().getY();
    }

}
