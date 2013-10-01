package com.deeep.sod2.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.utility.Logger;

import java.util.Random;

/**
 * Name: Particle
 * Pack: com.deeep.sod2.graphics
 * User: andreaskruhlmann
 * Date: 10/1/13
 */

public class Particle {

    /** Red green blue color*/
    private float r = 255.f, g = 255.f, b = 255.f;
    private float width, height;

    /** Used for fading particles, that do not die*/
    private double fade = 0;
    private boolean fadeGrowing = true;

    /** Ticks before ceasing to exist*/
    public double lifespan;

    /** Location vector (x,y)*/
    public PVector location;

    /** Velocity to add to position vector*/
    public PVector velocity;

    /** Velocity to add to velocity vector*/
    public PVector acceleration;

    /** Constructor when specified a particle color */
    public Particle(PVector v, Color c, float lifetime, float w, float h){
        location = v.get();
        acceleration = new PVector(0.0F,0.00F);
        velocity = new PVector(0.0F,0.0F);
        lifespan = lifetime;
        width = w;
        height = h;
        setColor(c);
        fade = new Random().nextFloat();
        fadeGrowing = new Random().nextBoolean();
    }

    /**
     * Updates the particle
     * @param delta delta to update the particle with
     */
    public void update(float delta){
        location.add(velocity);
        velocity.add(new PVector(acceleration.x*delta, acceleration.y*delta));
        if(lifespan!=-1)lifespan -= delta;
        else{
            if(fadeGrowing) fade+=delta/4;
            else fade-=delta/4;
        }
        if(fade < 0) fadeGrowing = true;
        if(fade > 1) fadeGrowing = false;
    }

    /**
     * Draw the particle
     * @param graphics SpriteBatch to be drawn with
     */
    public void draw(SpriteBatch graphics){
        if(lifespan<0 && lifespan!=-1) return;
        double t = lifespan/255;
        if(lifespan==-1) t=fade;
        ShapeRenderer.setColor(new Color(r, g, b, (float)t));
        ShapeRenderer.drawRectangle(graphics, location.x-2, location.y-2, width, height, true);
    }

    /**
     * Returns, whether or not the particle is dead. If lifespan is -1 then the
     * particle will live until removed and this will return false
     * @return whether the particle is "alive"
     */
    public boolean isDead(){
        return lifespan<0.0 && lifespan!=-1 ? true : false;
    }

    public void setHeight(float h){
        height = h;
    }

    public void setWidth(float w){
        width = w;
    }

    public float getHeight(){
        return height;
    }

    public float getWidth(){
        return width;
    }

    public Color getColor(){
        return new Color(r, g, b, lifespan<0.0 ? 255 : (float)lifespan);
    }

    public void setColor(Color c) {
        r = c.r;
        g = c.g;
        b = c.b;
    }

}