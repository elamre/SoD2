package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.Block;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.graphics.PVector;
import com.deeep.sod2.graphics.Particle;
import com.deeep.sod2.graphics.ParticleManager;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 7:46 PM
 */
public class World {
    /** TODO remove */
    public Block block;
    /** Todo move to a camera class */
    public Block camera;
    /** TODO remove */
    private Controller controller;

    private Map map;
    private EntityManager entityManager;
    private Grid grid;

    /** Min/Max offset for camera in units*/
    /** TODO Should be modified by the grid  in constructor */
    public static float minCamOffSetX = -2f, maxCamOffSetX, minCamOffSetY = -2f, maxCamOffSetY;

    /** TODO remove */
    private ParticleManager particleManager;

    /** Don't pay too much attention to this. This is just to test the camera and the view port */
    public World() {
        map = new Map();
        entityManager = new EntityManager();
        grid = new Grid(1, 20, 20, Color.BLUE);
            maxCamOffSetX = grid.getWidth() - 8;
            maxCamOffSetY = grid.getHeight() - 4;
        particleManager = new ParticleManager();
        block = new Block(0, -1, 1, 1);
        camera = new Block(0, -1, -2, -2);
        controller = new Controller();
        controller.registerKey(Input.Keys.W, new InputReactListener() {
            @Override
            public void inputReact() {
                block.y++;
            }
        }, InputReactListener.Event.PRESSED);
        controller.registerKey(Input.Keys.S, new InputReactListener() {
            @Override
            public void inputReact() {
                block.y--;
            }
        }, InputReactListener.Event.PRESSED);
        controller.registerKey(Input.Keys.A, new InputReactListener() {
            @Override
            public void inputReact() {
                block.x--;
            }
        }, InputReactListener.Event.PRESSED);
        controller.registerKey(Input.Keys.D, new InputReactListener() {
            @Override
            public void inputReact() {
                block.x++;
            }
        }, InputReactListener.Event.PRESSED);
        controller.registerKey(Input.Keys.UP, new InputReactListener() {
            @Override
            public void inputReact() {
                if(camera.y+.1f<maxCamOffSetY){
                    camera.y += .1f;
                    for(Particle p: particleManager.particles){
                        /** If the particle is a planet move it */
                        p.move(0.f, .001f*((float)p.getWidth()*3f*p.getHeight()*3f));
                    }
                }
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.DOWN, new InputReactListener() {
            @Override
            public void inputReact() {
                if(camera.y-.1f>=minCamOffSetY){
                    camera.y -= .1f;
                    for(Particle p: particleManager.particles){
                        /** If the particle is a planet move it */
                        p.move(0.f, -.001f*((float)p.getWidth()*2f*p.getHeight()*2f));
                    }
                }
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.LEFT, new InputReactListener() {
            @Override
            public void inputReact() {
                if(camera.x-.1f>=minCamOffSetX){
                    camera.x -= .1f;
                    for(Particle p: particleManager.particles){
                        /** If the particle is a planet move it */
                        p.move(-.001f*((float)p.getWidth()*2f*p.getHeight()*2f), 0.f);
                    }
                }
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.RIGHT, new InputReactListener() {
            @Override
            public void inputReact() {
                if(camera.x+.1f<maxCamOffSetX){
                    camera.x += .1f;
                    for(Particle p: particleManager.particles){
                        /** If the particle is a planet move it */
                        p.move(.001f*((float)p.getWidth()*2f*p.getHeight()*2f), 0.f);
                    }
                }
            }
        }, InputReactListener.Event.HOLD);
    }

    public void update(float deltaT) {
        controller.update();
        map.update();
        entityManager.update(deltaT);
        particleManager.update(deltaT);
    }

    public void draw(SpriteBatch spriteBatch, TextureRegion texture){
        particleManager.draw(spriteBatch);
        grid.draw(spriteBatch);
        block.draw(spriteBatch);
        spriteBatch.draw(texture, block.getX(), block.getY(), 1, 1);
        spriteBatch.draw(Assets.getAssets().getBitmapCharacter('0'),-1, -1, 1/5, 1/5);
    }
}
