package com.deeep.sod2.graphics;

/*
 * Class : ParticleManager
 * Package : com.deeep.sod2.graphics
 * Author : Andreas
 * Date : 01-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class ParticleManager {
    /** Particles ot be managed*/
    public ArrayList<Particle> particles;
    public Random random;

    public ParticleManager() {
        particles = new ArrayList<Particle>();
        random = new Random();

        for(int i=0; i< 10000; i++){
            particles.add(new Particle(new PVector((random.nextFloat()*1000-500)/40f, (random.nextFloat()*1000-500)/40f), Color.WHITE, -1, 1/40f, 1/40f));
        }

        int[] planetIds = {1,2,3,4,5,6,7,10,11,12,13,14,15,16,17,18,19,20};
        for(int i=0; i<100; i++){
            int id = planetIds[random.nextInt(planetIds.length)];
            float size = 128f-random.nextFloat()*50-random.nextFloat()*50;
            particles.add(new Planet(new PVector((random.nextFloat()*1000-500)/40f, (random.nextFloat()*1000-500)/40f), size*1/40f, size*1/40f, id));
        }
    }

    /**
     * Updates the particles
     * @param delta delta to update the particles with
     */
    public void update(float delta){
        for(Particle p: particles){
            p.update(delta);
        }
    }

    /**
     * Draw the particles
     * @param graphics SpriteBatch to be drawn with
     */
    public void draw(SpriteBatch graphics){
        for(Particle p: particles){
            p.draw(graphics);
        }
    }

}
