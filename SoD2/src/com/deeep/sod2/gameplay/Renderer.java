package com.deeep.sod2.gameplay;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Renderer {
    /** The frustrum width and height */
    private final int FRUSTRUM_WIDTH = 10;
    private final int FRUSTRUM_HEIGHT = 10;
    private OrthographicCamera cam;
    private SpriteBatch spriteBatch;
    private World world;

    public Renderer(SpriteBatch spriteBatch, World world) {
        this.world = world;
        cam = new OrthographicCamera(FRUSTRUM_WIDTH, FRUSTRUM_HEIGHT);
        cam.position.set(FRUSTRUM_WIDTH / 2, FRUSTRUM_HEIGHT / 2, 0);
        this.spriteBatch = spriteBatch;
    }

    public void render() {
        cam.update();
        spriteBatch.setProjectionMatrix(cam.combined);

    }
}
