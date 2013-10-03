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

public class Star extends Particle {

    /** to keep track of how much the camera has moved since it last updated the stars position*/
    private float oldCamX, oldCamY;

    public Star(PVector v, Color c, Sequencer sequencer, float w, float h) {
        super(v, c, sequencer, w, h);
    }

    /**
     * Updates the stars position
     * @param camera the camera of which the stars position should be adjusted to
     */
    public void updatePos(Block camera){
        location.x+=(camera.x-oldCamX)*0.9f;
        location.y+=(camera.y-oldCamY)*0.9f;

        oldCamX = camera.x;
        oldCamY = camera.y;
    }

}
