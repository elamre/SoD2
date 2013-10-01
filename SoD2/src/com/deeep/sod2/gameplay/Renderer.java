package com.deeep.sod2.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.graphics.ShapeRenderer;
import com.deeep.sod2.utility.Camera;
import com.deeep.sod2.utility.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Renderer {
    /** The frustum width and height. These are the blocks showed on screen */
    private final float FRUSTUM_WIDTH = Constants.VIRTUAL_WIDTH / (Constants.BLOCK_SIZE * Constants.SCALE);
    private final float FRUSTUM_HEIGHT = Constants.VIRTUAL_HEIGHT / (Constants.BLOCK_SIZE * Constants.SCALE);
    /** The camera controlling the viewing */
    private OrthographicCamera cam;
    /** The sprite batch to draw everything on */
    private SpriteBatch spriteBatch;
    /** The world to draw all the entities of */
    private World world;
    //--------------------Debugging stuff TODO remove -------------//
    /** Temporary texture to display something on the screen */
    private TextureRegion texture;
    //-------------------------------------------------------------//

    public Renderer(SpriteBatch spriteBatch, World world) {
        this.world = world;
        cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
        Camera.getInstance().setFrustum(cam.frustum);
        this.spriteBatch = spriteBatch;
        //TODO remove testing purposes only */
        texture = Assets.getAssets().getRegion("snakes/snake_1_head");
    }

    public void render() {
        cam.update();
        cam.position.set(FRUSTUM_WIDTH / 2 + world.camera.getX(), FRUSTUM_HEIGHT / 2 + world.camera.getY(), 0);
        spriteBatch.setProjectionMatrix(cam.combined);
        renderBackground();
        renderObjects();
    }

    private void renderBackground() {
        spriteBatch.disableBlending();
        spriteBatch.begin();
        //spriteBatch.draw(background, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        spriteBatch.enableBlending();
        spriteBatch.end();
    }

    private void renderObjects() {

        spriteBatch.begin();
        // ShapeRenderer.drawRectangle(spriteBatch,0,0,15,15,true);
        world.draw(spriteBatch, texture);

        spriteBatch.end();
    }
}
