package com.deeep.sod2.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Name: Background
 * Pack: com.deeep.sod2.graphics
 * User: andreaskruhlmann
 * Date: 10/1/13
 */
public class Background {

    public ArrayList<Particle> particles = new ArrayList<Particle>();

    public Background() {

    }

    public void draw(SpriteBatch g) {

    }

    public void update(int delta) {
        for(Particle p: particles){
            p.update(delta);
        }
    }

    public void removeStar(int side){
        switch (side){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }


}
