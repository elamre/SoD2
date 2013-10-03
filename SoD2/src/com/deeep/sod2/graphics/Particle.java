package com.deeep.sod2.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.particle.Sequence;
import com.deeep.sod2.particle.Sequencer;
import com.deeep.sod2.utility.Logger;

import java.util.Random;

/**
 * Name: Particle
 * Pack: com.deeep.sod2.graphics
 * User: andreaskruhlmann
 * Date: 10/1/13
 */

public class Particle {
    /** Location vector (x,y) */
    public PVector location;
    /** Velocity to add to position vector */
    public PVector velocity;
    /** Velocity to add to velocity vector */
    public PVector acceleration;
    /** Red green blue color */
    private float width, height, size;
    /** Used for fading particles, that do not die */
    private Sequencer sequencer;
    /** The color of the particles */
    private Color color;

    /** Constructor when specified a particle color */
    public Particle(PVector v, Color c, Sequencer sequencer, float w, float h) {
        location = v.get();
        acceleration = new PVector(0.0F, 0.00F);
        velocity = new PVector(0.0F, 0.0F);
        width = w;
        height = h;
        color = new Color(1, 1, 1, 1);
        setColor(c);
        this.sequencer = sequencer;
        size = (float) Math.sqrt(width * height);
    }

    /**
     * Updates the particle
     *
     * @param delta delta to update the particle with
     */
    public void update(float delta) {
        location.add(velocity);
        velocity.add(new PVector(acceleration.x * delta, acceleration.y * delta));
        if (sequencer != null)
            sequencer.update(delta);
    }

    /**
     * Move the particle directly
     *
     * @param x x movement
     * @param y y movement
     */
    public void move(float x, float y) {
        location.x += x;
        location.y += y;
    }

    /**
     * Draw the particle
     *
     * @param graphics SpriteBatch to be drawn with
     */
    public void draw(SpriteBatch graphics) {
        if (isDead())
            return;
        float alpha = 1;
        if (sequencer != null) {
            alpha = sequencer.getValue();
        }
        color.a -= alpha;
        ShapeRenderer.setColor(color);
        ShapeRenderer.drawRectangle(graphics, location.x - 2, location.y - 2, width, height, true);
        color.a += alpha;
        ShapeRenderer.setColor(color);
    }

    /**
     * Returns, whether or not the particle is dead. If lifespan is -1 then the
     * particle will live until removed and this will return false
     *
     * @return whether the particle is "alive"
     */
    public boolean isDead() {
        return (sequencer != null) ? sequencer.isFinished() : false;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float h) {
        height = h;
    }

    public float getSize() {
        return size;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float w) {
        width = w;
    }

    public void setColor(Color c) {
        if (color == null) return;
        color = c;
    }

    public int compareTo(Particle other) {
        double cSize = other.getSize();

        /** ascending order */
        /** return (int) (this.size - compareSalary); */

        /** descending order */
        return (int) (cSize - this.getSize());
    }

}