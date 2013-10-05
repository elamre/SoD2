package com.deeep.sod2.graphics;

/*
 * Class : ParticleManager
 * Package : com.deeep.sod2.graphics
 * Author : Andreas
 * Date : 01-10-13
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.gameplay.World;
import com.deeep.sod2.particle.FormulaTypes;
import com.deeep.sod2.particle.Sequence;
import com.deeep.sod2.particle.Sequencer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ParticleManager {
    /** Particles to be managed */
    public ArrayList<Particle> particles;

    public ParticleManager() {
        particles = new ArrayList<Particle>();
        Random random = new Random();

        float riseTime = 0;
        float downTime = 0;
        float maxBrightness = 0;
        float minBrightness = 0;
        float sleepTime = 0;

        for (int i = 0; i < 500; i++) {
            Sequencer sequencer = new Sequencer(true);
            riseTime = random.nextFloat() + 1f;
            downTime = random.nextFloat() + 1f;
            sleepTime = random.nextFloat() * 2;
            maxBrightness = Math.max(0.2f, random.nextFloat() - .1f);
            minBrightness = random.nextFloat() * 0.1f;
            while (maxBrightness < minBrightness)
                minBrightness -= 0.1f;
            sequencer.addSequence(new Sequence(new FormulaTypes.Linear(riseTime, maxBrightness)));
            sequencer.addSequence(new Sequence(new FormulaTypes.Sleep(sleepTime)));
            sequencer.addSequence(new Sequence(new FormulaTypes.Linear(downTime, minBrightness)));
            sequencer.addSequence(new Sequence(new FormulaTypes.Sleep(sleepTime)));
            particles.add(new Star(new PVector(-4 + (random.nextFloat() * (28)), -1 + (random.nextFloat() * (12))), Color.WHITE, sequencer, maxBrightness,1 / 40f, 1 / 40f));
        }

        int[] planetIds = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        for (int i = 0; i < 15; i++) {
            int id = planetIds[random.nextInt(planetIds.length)];
            float size = 120f - random.nextFloat() * 25 - random.nextFloat() * 25;
            particles.add(new Planet(new PVector((random.nextFloat() * 1000 - 500) / 40f, (random.nextFloat() * 1000 - 500) / 40f), size * 1 / 40f, size * 1 / 40f, id));
        }
        /** TODO planet sorting based on size*/
        //Collections.sort(particles);
    }

    public void addParticle(Particle particle) {
        particles.add(particle);
    }

    /**
     * Updates the particles
     *
     * @param delta delta to update the particles with
     */

    public void update(float delta) {
        for (Particle p : particles) {
            p.update(delta);
        }
    }

    /**
     * Draw the particles
     *
     * @param graphics SpriteBatch to be drawn with
     */
    public void draw(SpriteBatch graphics) {
        for (Particle p : particles) {
            p.draw(graphics);
        }
    }


}
