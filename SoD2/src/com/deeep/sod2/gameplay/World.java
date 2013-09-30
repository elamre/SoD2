package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Input;
import com.deeep.sod2.entities.Block;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class World {
    /** TODO remove */
    public Block block;
    /** Todo move to a camera class */
    public Block camera;
    /** TODO remove */
    private Controller controller;

    /** Dont pay too much attention to this. This is just to test the camera and the view port */
    public World() {
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
                camera.y += .1f;
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.DOWN, new InputReactListener() {
            @Override
            public void inputReact() {
                camera.y -= .1f;
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.LEFT, new InputReactListener() {
            @Override
            public void inputReact() {
                camera.x -= .1f;
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.RIGHT, new InputReactListener() {
            @Override
            public void inputReact() {
                camera.x += .1f;
            }
        }, InputReactListener.Event.HOLD);
    }

    public void update(float deltaT) {
        controller.update();
    }
}
