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
    public Block block;
    public Block camera;
    private Controller controller;

    public World() {
        block = new Block();
        camera = new Block();
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
